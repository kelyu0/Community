package proj.ssm.dao;

import org.apache.ibatis.annotations.Mapper;
import proj.ssm.entity.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    int selectCountByEntity(int entityType, int entityId);

}
