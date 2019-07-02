package com.woniuxy.yogasystem.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.yogasystem.dao.CoachDao;
import com.woniuxy.yogasystem.dao.MoneybagDao;
import com.woniuxy.yogasystem.dao.TraineeDao;
import com.woniuxy.yogasystem.dao.UserDao;
import com.woniuxy.yogasystem.dao.VenuesDao;
import com.woniuxy.yogasystem.pojo.Address;
import com.woniuxy.yogasystem.pojo.Apply;
import com.woniuxy.yogasystem.pojo.ApplyMessage;
import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Moneybag;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.User;
import com.woniuxy.yogasystem.pojo.Venues;
import com.woniuxy.yogasystem.service.UserService;
@Transactional
@Service("userService")
public class UserServiceImp implements UserService {
	@Resource
	private UserDao userdao;
	@Resource
	private TraineeDao traineeDao;
	@Resource
	private CoachDao coachDao;
	@Resource
	private VenuesDao venuesDao;
	@Resource
	private MoneybagDao moneybagDao;
	
	
	// 检测操作
	@Override
	public String checkacc(String acc) {
		String result = "手机号已被注册不可用";
		User user = userdao.checkacc(acc);
		if (user == null) {
			result = "手机号可用";
		}
		return result;
	}

	// 注册
	@Override
	public boolean register(User user) {
		boolean re = false;
		re = userdao.register(user);
		if(re){
			int uid = userdao.checkacc(user.getAcc()).getId();
			re = moneybagDao.addMon(uid);
		}
		return re;
	}

	// 登录
	@Override
	public User login(User user) {
		User user2 = null;

		if (userdao.checkacc(user.getAcc()) == null) {
			return user2;
		} else {
			user2 = userdao.checkMessageByAcc(user.getAcc());
		}
		return user2;
	}

	// 重置密码
	@Override
	public int reset(User user) {
		int i = 0;
		i = userdao.reset(user);
		return i;
	}

	// 注册管理员
	@Override
	public boolean regManager(User user) {
		boolean re = false;
		re = userdao.regManager(user);
		return re;
	}
	//短信登陆
		@Override
		public User mesLogin(User user) {
			User user2 = null;
			if (userdao.checkacc(user.getAcc()) == null) {
				return user2;
			}  else {
				user2 = userdao.checkMessageByAcc(user.getAcc());
				
					System.out.println("密码正确");
					return user2;		
			}
		}


	/*
	 * 管理员操作
	 */
	// 查询applymessage里所有教练申请请求
	@Override
	public List<ApplyMessage> findCoachApply() {

		return userdao.findCoachApply();
	}

	// 查询applymessage里所有场馆申请请求
	@Override
	public List<ApplyMessage> findVenuesApply() {

		return userdao.findVenuesApply();
	}

	// 同意并添加教练或场馆
	@Override
	public void addAgree(int uid, int role) {
		if (role == 1) {
			Apply coach = userdao.findMes(uid);
			if (coach != null) {
				if (userdao.addCoach(coach)) {
					if (userdao.deleteMes(uid) > 0 && userdao.deleteApply(uid) > 0) {
						userdao.roleupdate(role, uid);
					}
				}
			}
		} else if (role == 2) {
			Apply venues = userdao.findMes(uid);
			if (venues != null) {
				if (userdao.addVenues(venues)) {
					if (userdao.deleteMes(uid) > 0 && userdao.deleteApply(uid) > 0) {
						userdao.roleupdate(role, uid);
					}
				}
			}
		}

	}

	// 拒绝教练或场馆
	@Override
	public void refuse(int uid, int role) {
		int newrole = 5;// 替换成游客
		int i = 5;
		if (userdao.deleteMes(uid) > 0) {
			if (userdao.deleteApply(uid) > 0) {
				if (role == 1) {
					userdao.deleteAdd(uid);
					userdao.roleupdate(newrole, uid);
				} else if (role == 2) {
					userdao.deleteAdd(uid);
					userdao.deleteVenuesPicture(uid);
					userdao.roleupdate(newrole, uid);
				}
			}
		}

	}

	/*
	 * 学员操作
	 */
	// 补充学员资料
	@Override
	public String regTrainee(Trainee trainee, Address address, int role) {
		boolean re = false;
		String result = "失败";
		User user = userdao.checkMessageByAcc(trainee.getPhone());
		re = userdao.insertdefaultByUid(user);
		if (re) {
			if (userdao.updateDefault(trainee) > 0 && userdao.insertAdd(address)
					&& userdao.roleupdate(role, trainee.getUid()) > 0) {
				result = "成功";
			}
		}

		return result;
	}

	// 查询学员所有信息
	@Override
	public Trainee findTrainee(int uid) {
		Trainee trainee = userdao.findTrainee(uid);
		return trainee;
	}

	// 查询学员地址
	@Override
	public Address findTraineeAdd(int uid) {

		return userdao.findTraineeAdd(uid);
	}

	// 更新学员信息
	@Override
	public String updateTraineeMes(Trainee trainee, Address address) {
		String result = "失败";
		if (userdao.updateDefault(trainee) > 0 && userdao.updateAdd(address, trainee.getUid()) > 0) {
			result = "成功";
		}
		return result;
	}

	/*
	 * 教练操作
	 */
	// 教练补全资料
	@Override
	public String regCoach(Coach coach, Address address, int role) {
		boolean re = false;
		String result = "失败";
		User user = userdao.checkMessageByAcc(coach.getPhone());
		re = userdao.insertCoachdefaultByUid(user);
		if (re) {
			if (userdao.updateCoachDefault(coach) > 0 && userdao.insertAdd(address)
					&& userdao.roleupdate(role, coach.getUid()) > 0) {
				result = "成功";
			}
		}

		return result;
	}

	// 查询教练所有信息
	@Override
	public Coach findCoach(int uid) {
		Coach coach = userdao.findCoach(uid);
		return coach;
	}

	// 查询教练地址
	@Override
	public Address findCochAdd(int uid) {
		// TODO Auto-generated method stub
		return userdao.findCochAdd(uid);
	}

	// 修改教练资料
	@Override
	public String updateCoachMes(Coach coach, Address address) {
		String result = "失败";
		if (userdao.updateCoachDefault(coach) > 0 && userdao.updateAdd(address, coach.getUid()) > 0) {
			result = "成功";
		}
		return result;
	}

	// 申请成为教练
	@Override
	public String applyCoach(Coach coach, Address address, int role) {
		int i = 0;
		String result = "提交失败";// "申请已提交请耐心等待"
		if (userdao.applyCoach(coach) && userdao.insertAdd(address)) {// 如果已经插入到中间表apply表
			if (userdao.applyCoachMes(coach)) {// 把申请插入申请信息applymessage表
				userdao.roleupdate(role, coach.getUid());
				result = "申请已提交请耐心等待";
			}
		}
		return result;
	}

	/*
	 * 场馆操作
	 */

	@Override
	public String checkName(String name) {
		String result = "场馆已被注册！";
		if (userdao.checkName(name) == null) {
			if (userdao.checkNameFromApply(name) == null) {
				result = "场馆名称可用！";
			}

		}
		return result;
	}

	// 场馆补全资料
	@Override
	public String regVenues(Venues venues, Address address, int role, List<String> piclist) {
		boolean re = false;
		String result = "失败";
		User user = userdao.checkMessageByAcc(venues.getPhone());
		re = userdao.insertVenuesdefaultByUid(user);
		if (re) {
			if (userdao.updateVenuesDefault(venues) > 0) {
				for (int i = 0; i < piclist.size(); i++) {
					re = userdao.insertVenusePic(piclist.get(i), venues.getUid());
				}
				if (re) {
					userdao.insertAdd(address);

					userdao.roleupdate(role, venues.getUid());
					result = "成功";
				}
			}
		}
		return result;
	}

	// 申请成为场馆信息
	@Override
	public String applyVenues(Venues venues, Address address, int role, List<String> piclist) {
		boolean re = false;
		String result = "提交失败请联系管理员！";
		if (userdao.applyVenues(venues)) {
			for (int i = 0; i < piclist.size(); i++) {
				re = userdao.insertVenusePic(piclist.get(i), venues.getUid());
			}
			if (re) {
				userdao.insertAdd(address);
				userdao.applyVenuesMes(venues);
				userdao.roleupdate(role, venues.getUid());
				result = "申请已提交请耐心等待";
			}
		}
		return result;
	}

	// 查询场馆所有信息
	@Override
	public Venues findVenues(int uid) {
		Venues venues = userdao.findVenues(uid);
		return venues;
	}

	// 查询场馆地址
	@Override
	public Address findVenuesAdd(int uid) {
		// TODO Auto-generated method stub
		return userdao.findVenuesAdd(uid);
	}

	// 修改场馆资料
	@Override
	public String updateVenuesMes(Venues venues, Address address, List<String> piclist) {
		boolean re = false;
		String result = "失败";
		if (userdao.updateVenuesDefault(venues) > 0) {
			userdao.deleteVenuesPic(venues.getUid());
			for (int i = 0; i < piclist.size(); i++) {
				re = userdao.insertVenusePic(piclist.get(i), venues.getUid());
			}
			if (re) {
				userdao.updateAdd(address, venues.getUid());
				result = "成功";
			}
		}
		return result;
	}

	@Override
	public String findhead(int role, int uid) {
		String img = "";
		switch (role) {
		case 0:
			img = traineeDao.findHead(uid);
			break;
		case 1:
			img = coachDao.findHead(uid);
			break;
		case 2:
			img = venuesDao.findHead(uid);
			break;
		}
		System.out.println(img);
		return img;
	}
	
	
	
    public List<Coach> selectAllCoach() {
        return userdao.selectAllCoach();
    }

    
    public Coach selectCoach(Integer uid) {
        return userdao.selectCoach(uid);
    }

    
    public Trainee selectTrainee(Integer uid) {
        return userdao.selectTrainee(uid);
    }

    
    public Venues selectVenue(Integer uid) {
        return userdao.selectVenue(uid);
    }

    
    public List<Venues> selectAllVenues() {
        return userdao.selectAllVenues();
    }
    
    
    @Override
	public Map<String, Object> findAllInfoByUid(int uid) {
		// TODO Auto-generated method stub
		User user = userdao.findUserByUid(uid);
		int character = user.getRole();
		Map<String, Object>map=new HashMap<>();
		map.put("user", user);
		switch (character) {
		case 0:
			map.put("object", userdao.findTraineeByUid(uid));
			return map;
		
		case 1:
			map.put("object", userdao.findCoachByUid(uid));
			return map;
			
		case 2:
			map.put("object", userdao.findVenuesByUid(uid));
			return map;
		}
		return null;
	}
    
    
    
}
