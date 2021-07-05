package chapter02;

public class Assembler {
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	private MemberListService listSvc;
	private MemberSelectOneService selectOne;
	
	public Assembler() {
		memberDao = new MemberDao();
		regSvc = new MemberRegisterService(memberDao);
		pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
		listSvc = new MemberListService(memberDao);
		selectOne = new MemberSelectOneService();
		selectOne.setMemberDao(memberDao);
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberRegisterService getRegSvc() {
		return regSvc;
	}

	public ChangePasswordService getPwdSvc() {
		return pwdSvc;
	}

	public MemberListService getListSvc() {
		return listSvc;
	}
	
	public MemberSelectOneService getSelectOne() {
		return selectOne;
	}

}
