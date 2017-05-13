/**
 * 
 */
package org.wltea.analyzer.cfg;

import org.wltea.analyzer.dic.Dictionary;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Configuration {

	//是否启用智能分词
	private  boolean useSmart=true;

	//是否启用远程词典加载
	private boolean enableRemoteDict=false;

	//是否启用小写处理
	private boolean enableLowercase=true;

	public Configuration() {
		Dictionary.initial(this);
	}

	public Configuration(boolean useSmart, boolean enableLowercase, boolean enableRemoteDict) {
		this.useSmart = useSmart;
		this.enableLowercase = enableLowercase;
		this.enableRemoteDict = enableRemoteDict;

		Dictionary.initial(this);
	}

	public Path getConfigDir() {
		return Paths.get(".", "config");
	}

	public boolean isUseSmart() {
		return useSmart;
	}

	public Configuration setUseSmart(boolean useSmart) {
		this.useSmart = useSmart;
		return this;
	}

	public boolean isEnableRemoteDict() {
		return enableRemoteDict;
	}

	public boolean isEnableLowercase() {
		return enableLowercase;
	}
}
