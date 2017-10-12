package priv.sen.entry;

public class ${classname} {
	
	<#list colunms?keys as key>
	private ${colunms["${key}"]} ${key?lower_case};

	public ${colunms["${key}"]} get${key?lower_case?cap_first}() {
		return ${key?lower_case};
	}
	public void set${key?lower_case?cap_first} (${colunms["${key}"]} ${key?lower_case}) { 
		this.${key?lower_case} = ${key?lower_case};
	}

	</#list>
}