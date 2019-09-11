package kr.ac.konkuk.ccslab.cm.entity;

import java.util.Calendar;
import java.util.Hashtable;

import kr.ac.konkuk.ccslab.cm.info.CMInfo;
import kr.ac.konkuk.ccslab.cm.sns.CMSNSAttachAccessHistoryList;

/**
 * This class represents the information of a CM user (client).
 * 
 * @author mlim
 *
 */
public class CMUser extends CMObject {
	
	private int m_nID;
	private String m_strName;
	private String m_strPasswd;
	private String m_strHost;
	private int m_nUDPPort;
	private CMPosition m_pq;
	private String m_strCurrentSession;
	private String m_strCurrentGroup;
	private int m_nState;
	private CMChannelInfo<Integer> m_nonBlockSocketChannelInfo;
	private CMChannelInfo<Integer> m_blockSocketChannelInfo;
	private int m_nAttachDownloadScheme;	// 4 SERVER
	private CMSNSAttachAccessHistoryList m_historyList;	// 4 SERVER
	private Calendar m_lastLoginDate;		// 4 SERVER
	// 4 server (last event-transmission time of this object(client))
	private long m_lLastEventTransTime;
	// 4 myself (client or server) (last event-transmission time per receiver)
	private Hashtable<String, Long> m_myLastEventTransTimeHashtable;
	private int m_nKeepAliveTime;
	
	public CMUser()
	{
		m_nType = CMInfo.CM_USER;
		m_nID = -1;
		m_strName = "";
		m_strPasswd = "?";
		m_strHost = "?";
		m_nUDPPort = -1;
		m_strCurrentSession = "?";
		m_strCurrentGroup = "?";
		m_nState = CMInfo.CM_INIT;
		m_nonBlockSocketChannelInfo = new CMChannelInfo<Integer>();
		m_blockSocketChannelInfo = new CMChannelInfo<Integer>();
		m_nAttachDownloadScheme = -1;
		m_historyList = new CMSNSAttachAccessHistoryList();
		m_lastLoginDate = null;
		m_lLastEventTransTime = -1;
		m_myLastEventTransTimeHashtable = new Hashtable<String, Long>();
		m_nKeepAliveTime = -1;
	}
	
	public CMUser(String name, String passwd, String host)
	{
		m_nType = CMInfo.CM_USER;
		m_nID = -1;
		m_strName = name;
		m_strPasswd = passwd;
		m_strHost = host;
		m_nUDPPort = -1;
		m_strCurrentSession = "";
		m_strCurrentGroup = "";
		m_nState = CMInfo.CM_INIT;
		m_nonBlockSocketChannelInfo = new CMChannelInfo<Integer>();
		m_blockSocketChannelInfo = new CMChannelInfo<Integer>();
		m_nAttachDownloadScheme = -1;
		m_historyList = new CMSNSAttachAccessHistoryList();
		m_lastLoginDate = null;
		m_lLastEventTransTime = -1;
		m_myLastEventTransTimeHashtable = new Hashtable<String, Long>();
		m_nKeepAliveTime = -1;
	}
	
	// set methods
	public synchronized void setID(int id)
	{
		m_nID = id;
	}
	
	public synchronized void setName(String name)
	{
		m_strName = name;
	}
	
	public synchronized void setPasswd(String passwd)
	{
		m_strPasswd = passwd;
	}
	
	public synchronized void setHost(String host)
	{
		m_strHost = host;
	}
	
	public synchronized void setUDPPort(int port)
	{
		m_nUDPPort = port;
	}
	
	public synchronized void setPosition(CMPosition pq)
	{
		m_pq = pq;
	}
	
	public synchronized void setCurrentSession(String sName)
	{
		m_strCurrentSession = sName;
	}
	
	public synchronized void setCurrentGroup(String gName)
	{
		m_strCurrentGroup = gName;
	}

	public synchronized void setState(int state)
	{
		m_nState = state;
		
		switch(m_nState)
		{
		case CMInfo.CM_INIT:
		case CMInfo.CM_CONNECT:
			m_strName = "?";
			m_strPasswd = "?";
			m_strHost = "?";
			m_nUDPPort = -1;
			m_strCurrentSession = "?";
			m_strCurrentGroup = "?";
			break;
		case CMInfo.CM_LOGIN:
			m_strCurrentSession = "?";
			m_strCurrentGroup = "?";
			break;
		case CMInfo.CM_SESSION_JOIN:
			break;
		default:
			System.err.println("CMUser.setState(), invalid state ("+state+")");
		}
		
		return;
	}
	
	public synchronized void setAttachDownloadScheme(int scheme)
	{
		m_nAttachDownloadScheme = scheme;
	}
	
	public synchronized void setAttachAccessHistoryList(CMSNSAttachAccessHistoryList list)
	{
		m_historyList = list;
	}
	
	public synchronized void setLastLoginDate(Calendar date)
	{
		m_lastLoginDate = date;
	}
	
	public synchronized void setLastEventTransTime(long lTime)
	{
		m_lLastEventTransTime = lTime;
	}
	
	public synchronized void setMyLastEventTransTimeHashtable(Hashtable<String, Long> ht)
	{
		m_myLastEventTransTimeHashtable = ht;
	}
	
	public synchronized void setKeepAliveTime(int nSecond)
	{
		m_nKeepAliveTime = nSecond;
	}
	
	// get methods
	public synchronized int getID()
	{
		return m_nID;
	}
	
	public synchronized String getName()
	{
		return m_strName;
	}
	
	public synchronized String getPasswd()
	{
		return m_strPasswd;
	}
	
	public synchronized String getHost()
	{
		return m_strHost;
	}
	
	public synchronized int getUDPPort()
	{
		return m_nUDPPort;
	}
	
	public synchronized CMPosition getPosition()
	{
		return m_pq;
	}
	
	public synchronized String getCurrentSession()
	{
		return m_strCurrentSession;
	}
	
	public synchronized String getCurrentGroup()
	{
		return m_strCurrentGroup;
	}
	
	public synchronized int getState()
	{
		return m_nState;
	}
	
	public synchronized CMChannelInfo<Integer> getNonBlockSocketChannelInfo()
	{
		return m_nonBlockSocketChannelInfo;
	}
	
	public synchronized CMChannelInfo<Integer> getBlockSocketChannelInfo()
	{
		return m_blockSocketChannelInfo;
	}
	
	public synchronized int getAttachDownloadScheme()
	{
		return m_nAttachDownloadScheme;
	}
	
	public synchronized CMSNSAttachAccessHistoryList getAttachAccessHistoryList()
	{
		return m_historyList;
	}
	
	public synchronized Calendar getLastLoginDate()
	{
		return m_lastLoginDate;
	}
	
	public synchronized long getLastEventTransTime()
	{
		return m_lLastEventTransTime;
	}
	
	public synchronized Hashtable<String, Long> getMyLastEventTransTimeHashtable()
	{
		return m_myLastEventTransTimeHashtable;
	}
	
	public synchronized int getKeepAliveTime()
	{
		return m_nKeepAliveTime;
	}
}
