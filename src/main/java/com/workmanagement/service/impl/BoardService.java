package com.workmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmanagement.constant.SystemConstant;
import com.workmanagement.dto.BoardDTO;
import com.workmanagement.dto.NotificationDTO;
import com.workmanagement.dto.TaskGroupWithTaskDTO;
import com.workmanagement.dto.UserDTO;
import com.workmanagement.entity.BoardEntity;
import com.workmanagement.entity.UserEntity;
import com.workmanagement.exception.ResourceForbiddenException;
import com.workmanagement.exception.ResourceNotFoundException;
import com.workmanagement.mapper.BoardMapper;
import com.workmanagement.mapper.TaskGroupMapper;
import com.workmanagement.mapper.UserMapper;
import com.workmanagement.repository.BoardRepository;
import com.workmanagement.repository.UserRespository;
import com.workmanagement.service.IBoardService;
import com.workmanagement.utils.SecurityUtils;

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
	private TaskGroupMapper taskGroupMapper;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private SecurityUtils securityUtils;

	@Override
	@Transactional
	public BoardDTO save(BoardDTO dto) {
		return boardMapper.toDTO(boardRespository.save(boardMapper.toEntity(dto)));
	}

	@Override
	@Transactional
	public BoardDTO update(BoardDTO dto) {
		BoardEntity entity = boardRespository.findById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Not found board with id = " + dto.getId()));
		return boardMapper.toDTO(boardRespository.save(boardMapper.toEntity(dto, entity)));
	}

	@Override
	@Transactional
	public ResponseEntity<?> delete(long id) {
		long userid = securityUtils.getUserId();
		if (boardRespository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found board with id = " + id)).getOwner()
				.getId() != userid)
			throw new ResourceForbiddenException("Dont't have permisson");
		boardRespository.deleteById(id);
		return ResponseEntity.ok().body(null);
	}

	@Override
	public BoardDTO getBoardById(long id) {
		return boardMapper.toDTO(boardRespository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found board with id = " + id)));
	}

	@Override
	public List<BoardDTO> getAllBoardOfUser() {
		long id = securityUtils.getUserId();
		List<BoardDTO> boards = new ArrayList<>();
		UserEntity user = userRespository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found board with id = " + id));
//		for (BoardEntity board : user.getOwnerBoards())
//			boards.add(boardMapper.toDTO(board));
//		for (BoardEntity board : user.getBoards())
//			boards.add(boardMapper.toDTO(board));
		boards.addAll(user.getOwnerBoards().stream().map(s -> boardMapper.toDTO(s)).collect(Collectors.toList()));
		boards.addAll(user.getBoards().stream().map(s -> boardMapper.toDTO(s)).collect(Collectors.toList()));
		return boards;
	}

	@Override
	public List<UserDTO> getAllUserOfBoard(long id) {
		BoardEntity entity = boardRespository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found board with id = " + id));
		List<UserDTO> users = new ArrayList<>();
		users.add(userMapper.toDTO(entity.getOwner()));
		//entity.getUsers().forEach(user -> users.add(userMapper.toDTO(user)));
		users.addAll(entity.getUsers().stream().map(s -> userMapper.toDTO(s)).collect(Collectors.toList()));
		return users;
	}

	@Override
	public ResponseEntity<?> inviteUser(long boardId, long[] ids) {
		long id = securityUtils.getUserId();;
		BoardEntity entity = boardRespository.findById(boardId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found board with id = " + id));
		if (entity.getOwner().getId() != id)
			throw new ResourceForbiddenException("Dont't have permisson");
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
	@Transactional
	public BoardDTO addUser(long id, long notiId) {
		long userId = securityUtils.getUserId();
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

	@Override
	public List<TaskGroupWithTaskDTO> getAllGroupWithTaskOfBoard(long id) {
		//List<TaskGroupWithTaskDTO> groups = new ArrayList<>();
		BoardEntity boardEntity = boardRespository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found board with id = " + id));
//		for (TaskGroupEntity taskGroupEntity : boardEntity.getGroups())
//			groups.add(taskGroupMapper.toDetailDTO(taskGroupEntity));
		return boardEntity.getGroups().stream().map(s -> taskGroupMapper.toDetailDTO(s)).collect(Collectors.toList());
	}
}