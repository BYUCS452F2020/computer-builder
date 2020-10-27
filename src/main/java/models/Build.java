package models;

import java.util.Objects;

public class Build
{
	private String buildId;
	private String userId;
	private String buildName;


	// These are all the componentIds for each pc part
	private String motherboard;
	private String processor;
	private String cpuCooler;
	private String memory;
	private String storage;
	private String graphicsCard;
	private String powerSupply;
	private String pcCase;

	// If we want to change/add the Component objects instead of just the componentIds
//	private Component motherboard;
//	private Component processor;
//	private Component cpuCooler;
//	private Component memory;
//	private Component storage;
//	private Component graphicsCard;
//	private Component powerSupply;
//	private Component pcCase;


	public Build(String buildId, String userId, String buildName, String motherboard, String processor, String cpuCooler,
				 String memory, String storage, String graphicsCard, String powerSupply, String pcCase)
	{
		this.buildId = buildId;
		this.userId = userId;
		this.buildName = buildName;
		this.motherboard = motherboard;
		this.processor = processor;
		this.cpuCooler = cpuCooler;
		this.memory = memory;
		this.storage = storage;
		this.graphicsCard = graphicsCard;
		this.powerSupply = powerSupply;
		this.pcCase = pcCase;
	}


	public String getBuildId()
	{
		return buildId;
	}

	public void setBuildId(String buildId)
	{
		this.buildId = buildId;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getBuildName()
	{
		return buildName;
	}

	public void setBuildName(String buildName)
	{
		this.buildName = buildName;
	}

	public String getMotherboard()
	{
		return motherboard;
	}

	public void setMotherboard(String motherboard)
	{
		this.motherboard = motherboard;
	}

	public String getProcessor()
	{
		return processor;
	}

	public void setProcessor(String processor)
	{
		this.processor = processor;
	}

	public String getCpuCooler()
	{
		return cpuCooler;
	}

	public void setCpuCooler(String cpuCooler)
	{
		this.cpuCooler = cpuCooler;
	}

	public String getMemory()
	{
		return memory;
	}

	public void setMemory(String memory)
	{
		this.memory = memory;
	}

	public String getStorage()
	{
		return storage;
	}

	public void setStorage(String storage)
	{
		this.storage = storage;
	}

	public String getGraphicsCard()
	{
		return graphicsCard;
	}

	public void setGraphicsCard(String graphicsCard)
	{
		this.graphicsCard = graphicsCard;
	}

	public String getPowerSupply()
	{
		return powerSupply;
	}

	public void setPowerSupply(String powerSupply)
	{
		this.powerSupply = powerSupply;
	}

	public String getPcCase()
	{
		return pcCase;
	}

	public void setPcCase(String pcCase)
	{
		this.pcCase = pcCase;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Build build = (Build) o;
		return buildId.equals(build.buildId) &&
				userId.equals(build.userId) &&
				buildName.equals(build.buildName) &&
				motherboard.equals(build.motherboard) &&
				processor.equals(build.processor) &&
				cpuCooler.equals(build.cpuCooler) &&
				memory.equals(build.memory) &&
				storage.equals(build.storage) &&
				Objects.equals(graphicsCard, build.graphicsCard) &&
				powerSupply.equals(build.powerSupply) &&
				pcCase.equals(build.pcCase);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(buildId, userId, buildName, motherboard, processor, cpuCooler, memory, storage, graphicsCard, powerSupply, pcCase);
	}

	@Override
	public String toString()
	{
		return "Build{" +
				"buildId='" + buildId + '\'' +
				", userId='" + userId + '\'' +
				", buildName='" + buildName + '\'' +
				", motherboard='" + motherboard + '\'' +
				", processor='" + processor + '\'' +
				", cpuCooler='" + cpuCooler + '\'' +
				", memory='" + memory + '\'' +
				", storage='" + storage + '\'' +
				", graphicsCard='" + graphicsCard + '\'' +
				", powerSupply='" + powerSupply + '\'' +
				", pcCase='" + pcCase + '\'' +
				'}';
	}
}
