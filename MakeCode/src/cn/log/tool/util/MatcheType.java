/**
 * 
 */
package cn.log.tool.util;

/**
 * @author zouqone
 * @see HQL检索匹配模式
 * @createTime 2014-03-16
 */
public enum MatcheType {
	
	/**
	 * 相等
	 */
	EQUAL {     
		String getValue(){
			return "eq";
		}
	}, 
	
	/**
	 * 不相等
	 */
	NOTEQUAL {   
		String getValue(){
			return "not eq";
		}
	},
	
	/**
	 * %张%
	 */
	LIKE {      
		String getValue(){
			return "like";
		}
	}, 
	
	/**
	 * %张
	 */
	LLIKE {      
		String getValue(){
			return "llike";
		}
	}, 
	
	/**
	 * 伟%
	 */
	RLIKE {     
		String getValue(){
			return "rlike";
		}
	}, 
	
	/**
	 * 大于等于
	 */
	GE {     
		String getValue(){
			return "ge";
		}
	},
	
	/**
	 * 小于等于
	 */
	lE {      
		String getValue(){
			return "le";
		}
	}, 
	
	/**
	 * 大于
	 */
	GT {     
		String getValue(){
			return "gt";
		}
	}, 
	
	/**
	 * 小于
	 */
	lT {     
		String getValue(){
			return "lt";
		}
	},
	
	/**
	 * 介于...之间
	 */
	BETWEEN {      
		String getValue(){
			return "between";
		}
	},
	
	/**
	 * 在...区间之内
	 */
	IN {     
		String getValue(){
			return "in";
		}
	},
	
	/**
	 * 不在...区间之内
	 */
	NOTIN {      
		String getValue(){
			return "notin";
		}
	},
	
	/**
	 * 等于NULL
	 */
	ISNULL {      
		String getValue(){
			return "null";
		}
	},
	
	/**
	 * 不等于NULL
	 */
	ISNOTNULL {     
		String getValue(){
			return "not null";
		}
	},
	
	/**
	 * 并且
	 */
	AND {      
		String getValue(){
			return "and";
		}
	},
	
	/**
	 * 或者
	 */
	OR {      
		String getValue(){
			return "OR";
		}
	},
	
	/**
	 * 关联属性eq查询
	 */
	ASSOCIATE_EQ {      
		String getValue(){
			return "associate_eq";
		}
	},
	
	/**
	 * 关联属性like查询
	 */
	ASSOCIATE_LIKE {    
		String getValue(){
			return "associate_like";
		}
	};
	
	/**
	 * 抽象方法获取值
	 * @return
	 */
	abstract String getValue();
}
