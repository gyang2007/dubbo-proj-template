package com.gyang.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 流水号生成
 * 
 * @author yinfeng.guo
 * 
 */
public class MsgIdGenerator {
	// 静态单例
	private static MsgIdGenerator self = new MsgIdGenerator();
	// 时间格式
	private final String dateFormat = "MMddHHmmss";
	private static final String seperator = "-";
	// 来源
	private String source = "unknow";
	// 序号
	private int seq = 1;

	private static final long max = 9999999999l;

	private MsgIdGenerator() {
		// client id
		try {
			InetAddress local = InetAddress.getLocalHost();
			String hostName = local.getHostName();
			hostName = hostName.replace("l-", "").replaceFirst("\\.f.*$", "");
			if (hostName.length() > 10) {
				hostName = hostName.substring(hostName.length() - 10);
			}

			this.source = StringUtils.leftPad(hostName, 10, "+");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static MsgIdGenerator getInstance() {
		return self;
	}

	private synchronized int incrementAndGet() {
		if (this.seq == max) {
			this.seq = 1;
		}

		return seq++;
	}

	public static class MsgId {
		private String id = null;
		private AtomicInteger seqId = new AtomicInteger(0);

		public MsgId(String id) {
			super();
			this.id = id;
		}

		public String getId() {
			return id;
		}

		public String nextSeq() {
			return id + seperator + StringUtils.leftPad(String.valueOf(seqId.incrementAndGet()), 3, '0');
		}
	}

	public MsgId nextMsgId() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.source);
		sb.append(seperator);
		sb.append(DateFormatUtils.format(System.currentTimeMillis(), this.dateFormat));
		sb.append(seperator);
		sb.append(StringUtils.leftPad(String.valueOf(this.incrementAndGet()), 10, '0'));

		return new MsgId(sb.toString());
	}
}
