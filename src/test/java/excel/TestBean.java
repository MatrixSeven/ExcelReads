package excel;


import seven.wapperInt.anno.ExcelAnno;
import seven.wapperInt.anno.RegHelper;

public class TestBean {

	@ExcelAnno(Value="时间",Required="_REG_EMAIL")	
	private String time;
	@ExcelAnno(Value="金额",Required=RegHelper._REG_INT)
	private String moeny;
	@ExcelAnno(Value="在线人数",Required=RegHelper._REG_STRING)
	private String onlines;
	@ExcelAnno(Value="服务IP")
	private String servicrs;
	@ExcelAnno(Value="地区")
	private String part;
	@ExcelAnno(Value="创建人")
	private String createPerson;
	@ExcelAnno(Value="连接类型")
	private String linkType;
	@ExcelAnno(Value="岁数",Required=RegHelper._REG_DATE)
	private String age;
	
	
	
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMoeny() {
		return moeny;
	}
	public void setMoeny(String moeny) {
		this.moeny = moeny;
	}
	public String getOnlines() {
		return onlines;
	}
	public void setOnlines(String onlines) {
		this.onlines = onlines;
	}
	public String getServicrs() {
		return servicrs;
	}
	public void setServicrs(String servicrs) {
		this.servicrs = servicrs;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	@Override
	public String toString() {
		return "TestBean [time=" + time + ", moeny=" + moeny + ", onlines=" + onlines + ", servicrs=" + servicrs
				+ ", part=" + part + ", createPerson=" + createPerson + ", linkType=" + linkType + ", age=" + age + "]";
	}
	
	
}
