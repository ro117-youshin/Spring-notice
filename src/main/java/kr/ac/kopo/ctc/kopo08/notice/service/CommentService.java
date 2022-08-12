package kr.ac.kopo.ctc.kopo08.notice.service;

import kr.ac.kopo.ctc.kopo08.notice.domain.Comment;

public interface CommentService {
	public Comment findById(Long id);
	public Comment save(Comment comment);
	public Comment deleteById(Long id);
}
