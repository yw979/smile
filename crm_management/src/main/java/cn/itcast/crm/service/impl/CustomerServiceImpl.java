package cn.itcast.crm.service.impl;

import java.util.List;







import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.crm.dao.CustomerRepository;
import cn.itcast.crm.domain.Customer;
import cn.itcast.crm.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	// 注入dao
	@Autowired
	private CustomerRepository customerrepository;

	@Override
	public List<Customer> findNoAssociationCustomers() {
		// fixedAreaId is null

		return customerrepository.findByFixedAreaIdIsNull();
	}

	@Override
	public List<Customer> findHasAssociationFixedAreaCustomers(
			String fixedAreaId) {
		// fixedAreaId=?
		return customerrepository.findByFixedAreaId(fixedAreaId);
	}

	@Override
	public void associationCustomersToFixedArea(String customerIdStr,
			String fixedAreaId) {
		// 解除关联操作
		customerrepository.clearFixedAreaId(fixedAreaId);

		// 如果customerIdStr为空,则会报错,所以这里需要判断一下
		if (StringUtils.isBlank(customerIdStr)) {
			return;
		}
		// 切割字符串1,2,3
		String[] customerIdArray = customerIdStr.split(",");
		for (String idStr : customerIdArray) {
			Integer id = Integer.parseInt(idStr);
			customerrepository.updateFixedAreaId(fixedAreaId, id);
		}

	}

	@Override
	public void regist(Customer customer) {
		customerrepository.save(customer);
		
	}

	@Override
	public Customer findByTelephone(String telephone) {
		return customerrepository.findByTelephone(telephone);
	}

	@Override
	public void updateType(String telephone) {
		customerrepository.updateType(telephone);
		
	}

	@Override
	public Customer login(String telephone, String password) {
		return customerrepository.findByTelephoneAndPassword(telephone,password);
	}

}
