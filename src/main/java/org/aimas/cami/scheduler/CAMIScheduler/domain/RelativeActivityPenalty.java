package org.aimas.cami.scheduler.CAMIScheduler.domain;

import java.util.List;

import org.aimas.cami.scheduler.CAMIScheduler.utils.AbstractPersistable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author Bogdan
 *
 */
@XStreamAlias("RelativeActivityPenalty")
public class RelativeActivityPenalty extends AbstractPersistable {

	private RelativeType relativeType;
	private ActivityType relativeActivityType;
	private ActivityType staticActivityType;

	public RelativeType getRelativeType() {
		return relativeType;
	}

	public void setRelativeType(RelativeType relativeType) {
		this.relativeType = relativeType;
	}

	public ActivityType getRelativeActivityType() {
		return relativeActivityType;
	}

	public void setRelativeActivityType(ActivityType relativeActivityType) {
		this.relativeActivityType = relativeActivityType;
	}

	public ActivityType getStaticActivityType() {
		return staticActivityType;
	}

	public void setStaticActivityType(ActivityType staticActivityType) {
		this.staticActivityType = staticActivityType;
	}

	@Override
	public String toString() {
		return "RelativeActivityPenalty [relativeType=" + relativeType + ", relativeActivityType="
				+ relativeActivityType + ", staticActivityType=" + staticActivityType + "]";
	}

}
