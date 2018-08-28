package cn.itcast.crm.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.opensymphony.xwork2.validator.annotations.CustomValidator;

import cn.itcast.crm.domain.Customer;

public interface CustomerService {

	// 查询所有未关联客户列表
	@Path("/noassociationcustomers")
	@GET
	@Produces({ "application/xml", "application/json" })
	public List<Customer> findNoAssociationCustomers();

	// 已经关联到指定定区的客户列表
	@Path("/associationfixedareacustomers/{fixedareaid}")
	@GET
	@Produces({ "application/xml", "application/json" })
	public List<Customer> findHasAssociationFixedAreaCustomers(
			@PathParam("fixedareaid") String fixedAreaId);

	// 将客户关联到定区上.将所有客户的id,拼成字符串1,2,3
	@Path("/associationcustomerstofixedarea")
	@PUT
	public void associationCustomersToFixedArea(
			@QueryParam("customerIdStr") String customerIdStr,
			@QueryParam("fixedAreaId") String fixedAreaId);
	
	//编写CRM 的 WebService 接口，实现客户信息保存操作
	@Path("/customer")
	@POST
	@Consumes({ "application/xml", "application/json" })
	public void regist(Customer customer);

	@Path("/customer/telephone/{telephone}")
	@GET
	@Consumes({ "application/xml", "application/json" }) 	
	public Customer findByTelephone(@PathParam("telephone") String telephone);
	
	@Path("/customer/updateType/{telephone}")
	@GET
	public void updateType(@PathParam("telephone") String telephone);
	
	//编写 crm_management 的 CustomerService 添加 WebService 方法 login
	@Path("/customer/login")
	@GET
	@Consumes({ "application/xml", "application/json" }) 	
	public Customer login(@QueryParam("telephone") String telephone,
			@QueryParam("password") String password);
}
