package com.workmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmanagement.api.response.ErrorResponse;
import com.workmanagement.constant.SystemConstant;
import com.workmanagement.dto.BoardDTO;
import com.workmanagement.dto.NotificationDTO;
import com.workmanagement.dto.UserDTO;
import com.workmanagement.entity.BoardEntity;
import com.workmanagement.entity.UserEntity;
import com.workmanagement.mapper.BoardMapper;
import com.workmanagement.mapper.UserMapper;
import com.workmanagement.respository.BoardRepository;
import com.workmanagement.respository.UserRespository;
import com.workmanagement.security.CustomUserDetail;
import com.workmanagement.service.IBoardService;

@Service
public class BoardService implements IBoardService {

	@Autowired
	private BoardRepository boardRespository;

	@Autowired
	private UserRespository userRespository;

	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Override
	@Transactional
	public BoardDTO save(BoardDTO dto) {
		return boardMapper.toDTO(boardRespository.save(boardMapper.toEntity(dto)));
	}

	@Override
	@Transactional
	public BoardDTO update(BoardDTO dto) {
		BoardEntity entity = boardRespository.findById(dto.getId()).orElse(null);
		return boardMapper.toDTO(boardRespository.save(boardMapper.toEntity(dto, entity)));
	}

	@Override
	@Transactional
	public ResponseEntity<?> delete(long id) {
		long userid = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getId();
		if (boardRespository.findById(id).orElse(null).getOwner().getId() != userid)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(
					Integer.toString(HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN.name(), "/board"));
		boardRespository.deleteById(id);
		return ResponseEntity.ok().body(null);
	}

	@Override
	@Transactional
	public BoardDTO getBoardById(long id) {
		return boardMapper.toDTO(boardRespository.findById(id).orElse(null));
	}

	@Override
	@Transactional
	public List<BoardDTO> getAllBoardOfUser() {
		long id = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		List<BoardDTO> boards = new ArrayList<>();
		UserEntity user = userRespository.findById(id).orElse(null);
		for (BoardEntity board : user.getOwnerBoards())
			boards.add(boardMapper.toDTO(board));
		for (BoardEntity board : user.getBoards())
			boards.add(boardMapper.toDTO(board));
		return boards;
	}

	@Override
	public List<UserDTO> getAllUserOfBoard(long id) {
		BoardEntity entity = boardRespository.findById(id).orElse(null);
		List<UserDTO> users = new ArrayList<>();
		users.add(userMapper.toDTO(entity.getOwner()));
		entity.getUsers().forEach(user -> users.add(userMapper.toDTO(user)));
		return users;
	}

	@Override
	public ResponseEntity<?> inviteUser(long boardId, long[] ids) {
		long id = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		BoardEntity entity = boardRespository.findById(boardId).orElse(null);
		if (entity.getOwner().getId() != id)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		for (long userId : ids) {
			String message = "You have been invited to " + entity.getName();
			String thumbnail = entity.getOwner().getAvatar();
			NotificationDTO dto = notificationService.createNotification(message, thumbnail, boardId, userId,
					SystemConstant.INVITE_NOTIFICATION);
			messagingTemplate.convertAndSend("/notifications/" + userId, dto);
		}
		return ResponseEntity.ok().body("Invite successfully");
	}

	@Override
	public BoardDTO addUser(long id, long notiId) {
		long userId = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getId();
		if (notificationService.isAccept(notiId))
			return null;
		notificationService.setIsAccept(notiId);
		BoardEntity entity = boardRespository.findById(id).orElse(null);
		UserEntity user = userRespository.findById(userId).orElse(null);
		entity.getUsers()
				.forEach(member -> messagingTemplate.convertAndSend("/notifications/" + member.getId(),
						notificationService.createNotification(user.getDisplayName() + " joined " + entity.getName(),
								user.getAvatar(), -1, member.getId(), SystemConstant.MESSAGE_NOTIFICATION)));
		messagingTemplate.convertAndSend("/notifications/" + entity.getOwner().getId(),
				notificationService.createNotification(user.getDisplayName() + " joined " + entity.getName(),
						user.getAvatar(), -1, entity.getOwner().getId(), SystemConstant.MESSAGE_NOTIFICATION));
		entity.getUsers().add(user);
		return boardMapper.toDTO(boardRespository.save(entity));
	}
}