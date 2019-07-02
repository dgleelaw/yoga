package com.woniuxy.yogasystem.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.yogasystem.dao.FollowsDao;
import com.woniuxy.yogasystem.pojo.Follows;
import com.woniuxy.yogasystem.service.FollowsService;
@Transactional
@Service("FollowsService")
public class FollowsServiceImpl implements FollowsService {
	@Autowired
	private FollowsDao followsDao;

	// 查询互相关注的人的uid
	@Override
	public List<Follows> FindAllFollowsUidByOwnerUid(Integer uid) {
		// TODO Auto-generated method stub

		return followsDao.FindAllFollowsUidByOwnerUid(uid);
	}

	@Override
	// 修改显示信息的权限,当页面修改权限时
	public void UpdateStatus(int status, int followuid) {
		// TODO Auto-generated method stub
		followsDao.UpdateStatus(status, followuid);
	}

	@Override
	//先user和follows连表查询建立follows,然后follows插入关注表
	public void insertFollows(Follows follows) {
		// TODO Auto-generated method stub
		followsDao.insertFollows(follows);
		
	}

	@Override
	public void deleteFollows(Follows follows) {
		// TODO Auto-generated method stub
		followsDao.deleteFollows(follows);
	}

	@Override
	public List<Follows> findAllFollows(int uid) {
		// TODO Auto-generated method stub
		return followsDao.findAllFollows(uid);
	}

	@Override
	public List<Follows> findMyFollows(int uid) {
		// TODO Auto-generated method stub
		return followsDao.findMyFollows(uid);
	}

	@Override
	public int findFollowByUid(int uid, int otherUid) {
		// 0 不是好友，1是好友
				int relation = 0;
				// 我的关注名单是否有被查询资料人的ID；
				int[] followedId = null;
				// 被查询人的关注里是否有我
				int[] myId = null;
				// 我的关注名单是否有被查询资料人的ID；
				followedId = followsDao.findFollowidByUid(uid);
				for (int i = 0; i < followedId.length; i++) {
					// 如果我关注了被查询人，再查看被查询人是否关注了我
					if (followedId[i] == otherUid) {
						// 被查询人的关注里是否有我
						myId = followsDao.findFollowidByUid(otherUid);
						for (int j = 0; j < myId.length; j++) {
							// 被查询人的关注里有我
							if (myId[j] == uid) {
								relation = 1;
								return relation;
							} else {
								break;
							}
						}
					} else {
						break;
					}
				}
				return relation;
	}

}
