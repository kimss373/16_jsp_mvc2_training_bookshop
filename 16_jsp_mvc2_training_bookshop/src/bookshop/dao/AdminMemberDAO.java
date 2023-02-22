package bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bookshop.dto.AdminMemberDTO;
import bookshop.dto.MemberDTO;

public class AdminMemberDAO {

	private AdminMemberDAO() {}
	private static AdminMemberDAO instance = new AdminMemberDAO();
	public static AdminMemberDAO getInstance() {
		return instance;
	}

	private Connection conn         = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs            = null;

	public void getConnection() {
		
		try {
			
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");       
			DataSource ds = (DataSource) envctx.lookup("jdbc/bookshop"); 		  
			conn = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void getClose() {
    	if (rs != null)    {try {rs.close();}   catch (SQLException e) {}}
    	if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
        if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
    }
	
	
	 public boolean adminLogin(AdminMemberDTO adminMemberDTO) {
	    	
    	boolean isLogin = false;
    	
    	try {
    		
			getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM `ADMIN` WHERE ADMIN_ID = ? AND PASSWD = ?");
			pstmt.setString(1, adminMemberDTO.getAdminId());
			pstmt.setString(2, adminMemberDTO.getPasswd());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				isLogin = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
    	
    	return isLogin;
	    	
	 }
	 
	 public ArrayList<MemberDTO> getMemberList(int viewCnt) {
		 ArrayList<MemberDTO> memberDTOList = new ArrayList<MemberDTO>();
		 
		 try {
			getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMemberId(rs.getString("MEMBER_ID"));
				memberDTO.setMemberNm(rs.getString("MEMBER_NM"));
				memberDTO.setPasswd(rs.getString("PASSWD"));
				memberDTO.setSex(rs.getString("SEX"));
				memberDTO.setBirthDt(rs.getString("BIRTH_DT"));
				memberDTO.setHp(rs.getString("HP"));
				memberDTO.setSmsstsYn(rs.getString("SMSSTS_YN"));
				memberDTO.setEmail(rs.getString("EMAIL"));
				memberDTO.setEmailstsYn(rs.getString("EMAILSTS_YN"));
				memberDTO.setZipcode(rs.getString("ZIPCODE"));
				memberDTO.setRoadAddress(rs.getString("ROAD_ADDRESS"));
				memberDTO.setJibunAddress(rs.getString("JIBUN_ADDRESS"));
				memberDTO.setNamujiAddress(rs.getString("NAMUJI_ADDRESS"));
				memberDTO.setPoint(rs.getInt("POINT"));
				memberDTO.setJoinDt(rs.getDate("JOIN_DT"));
				memberDTOList.add(memberDTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		 
		 
		 return memberDTOList;
	 }
	 
	 public ArrayList<MemberDTO> getMemberList(String sort, String searchWord, int viewCnt, int page) {
		 
		 ArrayList<MemberDTO> memberDTOList = new ArrayList<MemberDTO>();
		 
		 try {
			getConnection();
			
			if(sort.equals("name")) {
				
				pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE MEMBER_NM LIKE ?");
				pstmt.setString(1, "%" + searchWord + "%");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setMemberId(rs.getString("MEMBER_ID"));
					memberDTO.setMemberNm(rs.getString("MEMBER_NM"));
					memberDTO.setPasswd(rs.getString("PASSWD"));
					memberDTO.setSex(rs.getString("SEX"));
					memberDTO.setBirthDt(rs.getString("BIRTH_DT"));
					memberDTO.setHp(rs.getString("HP"));
					memberDTO.setSmsstsYn(rs.getString("SMSSTS_YN"));
					memberDTO.setEmail(rs.getString("EMAIL"));
					memberDTO.setEmailstsYn(rs.getString("EMAILSTS_YN"));
					memberDTO.setZipcode(rs.getString("ZIPCODE"));
					memberDTO.setRoadAddress(rs.getString("ROAD_ADDRESS"));
					memberDTO.setJibunAddress(rs.getString("JIBUN_ADDRESS"));
					memberDTO.setNamujiAddress(rs.getString("NAMUJI_ADDRESS"));
					memberDTO.setPoint(rs.getInt("POINT"));
					memberDTO.setJoinDt(rs.getDate("JOIN_DT"));
					memberDTOList.add(memberDTO);
				}
				
				
			} else if (sort.equals("point")) {
				
				pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE POINT >= ?");
				pstmt.setInt(1, Integer.parseInt(searchWord));
				rs = pstmt.executeQuery();
				while (rs.next()) {
					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setMemberId(rs.getString("MEMBER_ID"));
					memberDTO.setMemberNm(rs.getString("MEMBER_NM"));
					memberDTO.setPasswd(rs.getString("PASSWD"));
					memberDTO.setSex(rs.getString("SEX"));
					memberDTO.setBirthDt(rs.getString("BIRTH_DT"));
					memberDTO.setHp(rs.getString("HP"));
					memberDTO.setSmsstsYn(rs.getString("SMSSTS_YN"));
					memberDTO.setEmail(rs.getString("EMAIL"));
					memberDTO.setEmailstsYn(rs.getString("EMAILSTS_YN"));
					memberDTO.setZipcode(rs.getString("ZIPCODE"));
					memberDTO.setRoadAddress(rs.getString("ROAD_ADDRESS"));
					memberDTO.setJibunAddress(rs.getString("JIBUN_ADDRESS"));
					memberDTO.setNamujiAddress(rs.getString("NAMUJI_ADDRESS"));
					memberDTO.setPoint(rs.getInt("POINT"));
					memberDTO.setJoinDt(rs.getDate("JOIN_DT"));
					memberDTOList.add(memberDTO);
				}
			} else if (sort.equals("year")) {
				pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE JOIN_DT >= ?");
				pstmt.setString(1, searchWord + "-01-01");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setMemberId(rs.getString("MEMBER_ID"));
					memberDTO.setMemberNm(rs.getString("MEMBER_NM"));
					memberDTO.setPasswd(rs.getString("PASSWD"));
					memberDTO.setSex(rs.getString("SEX"));
					memberDTO.setBirthDt(rs.getString("BIRTH_DT"));
					memberDTO.setHp(rs.getString("HP"));
					memberDTO.setSmsstsYn(rs.getString("SMSSTS_YN"));
					memberDTO.setEmail(rs.getString("EMAIL"));
					memberDTO.setEmailstsYn(rs.getString("EMAILSTS_YN"));
					memberDTO.setZipcode(rs.getString("ZIPCODE"));
					memberDTO.setRoadAddress(rs.getString("ROAD_ADDRESS"));
					memberDTO.setJibunAddress(rs.getString("JIBUN_ADDRESS"));
					memberDTO.setNamujiAddress(rs.getString("NAMUJI_ADDRESS"));
					memberDTO.setPoint(rs.getInt("POINT"));
					memberDTO.setJoinDt(rs.getDate("JOIN_DT"));
					memberDTOList.add(memberDTO);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		 
		 return memberDTOList;
		 
	 }
	 
	 public MemberDTO getMemberDetail(String memberId) {
	        
	    	MemberDTO memberDTO = new MemberDTO();
	        
	    	try {
	    		
	            getConnection();
	            pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE MEMBER_ID = ?");
	            pstmt.setString(1, memberId);
	            rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	            	memberDTO = new MemberDTO();
	            	memberDTO.setMemberId(rs.getString("MEMBER_ID"));
	            	memberDTO.setMemberNm(rs.getString("MEMBER_NM"));
	            	memberDTO.setPasswd(rs.getString("PASSWD"));
	            	memberDTO.setSex(rs.getString("SEX"));
	            	memberDTO.setBirthDt(rs.getString("BIRTH_DT"));
	            	memberDTO.setHp(rs.getString("HP"));
	            	memberDTO.setSmsstsYn(rs.getString("SMSSTS_YN"));
	            	memberDTO.setEmail(rs.getString("EMAIL"));
	            	memberDTO.setEmailstsYn(rs.getString("EMAILSTS_YN"));
	            	memberDTO.setZipcode(rs.getString("ZIPCODE"));
	            	memberDTO.setRoadAddress(rs.getString("ROAD_ADDRESS"));
	            	memberDTO.setJibunAddress(rs.getString("JIBUN_ADDRESS"));
	            	memberDTO.setNamujiAddress(rs.getString("NAMUJI_ADDRESS"));
	            	memberDTO.setPoint(rs.getInt("POINT"));
	            	memberDTO.setJoinDt(rs.getDate("JOIN_DT"));
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        	getClose();
	        }
	    	
	        return memberDTO;
	    
	    }
	 
	public void updateMember(MemberDTO memberDTO) {
	        
			try {
	    		
	            getConnection();
	            
	            String sql = "UPDATE MEMBER SET ";
			           sql += "POINT = ?,";
			           sql += "PASSWD = ?,";
			           sql += "MEMBER_NM = ?,";
	                   sql += "SEX = ?,";
	                   sql += "BIRTH_DT = ?,";
	                   sql += "HP = ?,";
	                   sql += "SMSSTS_YN = ?,";
	                   sql += "EMAIL = ?,";
	                   sql += "EMAILSTS_YN = ?,";
	                   sql += "ZIPCODE = ?,";
	                   sql += "ROAD_ADDRESS = ?,";
	                   sql += "JIBUN_ADDRESS = ?,";
	                   sql += "NAMUJI_ADDRESS = ?";
	                   sql += "WHERE MEMBER_ID = ?";
	                   
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, memberDTO.getPoint());
	            pstmt.setString(2, memberDTO.getPasswd());
	            pstmt.setString(3, memberDTO.getMemberNm());
	            pstmt.setString(4, memberDTO.getSex());
	            pstmt.setString(5, memberDTO.getBirthDt());
	            pstmt.setString(6, memberDTO.getHp());
	            pstmt.setString(7, memberDTO.getSmsstsYn());
	            pstmt.setString(8, memberDTO.getEmail());
	            pstmt.setString(9, memberDTO.getEmailstsYn());
	            pstmt.setString(10, memberDTO.getZipcode());
	            pstmt.setString(11, memberDTO.getRoadAddress());
	            pstmt.setString(12, memberDTO.getJibunAddress());
	            pstmt.setString(13, memberDTO.getNamujiAddress());
	            pstmt.setString(14, memberDTO.getMemberId());
	            pstmt.executeUpdate();
	            
	        } catch(Exception e) {
	            e.printStackTrace();
	        } finally {
	        	getClose();
	        }
	 }
	
	public void deleteMember(String memberId) {
		
		try {
			getConnection();
			
			pstmt = conn.prepareStatement("DELETE FROM MEMBER WHERE MEMBER_ID = ?");
			pstmt.setString(1, memberId);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		
	}
	
	
	
}
