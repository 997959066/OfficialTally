package cn.xiaoyu.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

 @Getter
 @Setter
public class TokenMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String g_token;
	private String g_userId;
	private String g_time;

	@Override
	public String toString() {
		return "TokenMessage [g_token=" + g_token + ", g_userId=" + g_userId + ", g_time=" + g_time + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((g_time == null) ? 0 : g_time.hashCode());
		result = prime * result + ((g_token == null) ? 0 : g_token.hashCode());
		result = prime * result + ((g_userId == null) ? 0 : g_userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenMessage other = (TokenMessage) obj;
		if (g_time == null) {
			if (other.g_time != null)
				return false;
		} else if (!g_time.equals(other.g_time))
			return false;
		if (g_token == null) {
			if (other.g_token != null)
				return false;
		} else if (!g_token.equals(other.g_token))
			return false;
		if (g_userId == null) {
			if (other.g_userId != null)
				return false;
		} else if (!g_userId.equals(other.g_userId))
			return false;
		return true;
	}
}
