package org.aimas.cami.scheduler.CAMIScheduler.domain.solver;

import org.aimas.cami.scheduler.CAMIScheduler.domain.Activity;
import org.aimas.cami.scheduler.CAMIScheduler.domain.ActivityPeriod;
import org.aimas.cami.scheduler.CAMIScheduler.domain.RelativeActivity;
import org.aimas.cami.scheduler.CAMIScheduler.utils.AdjustActivityPeriod;
import org.optaplanner.core.impl.domain.variable.listener.VariableListener;
import org.optaplanner.core.impl.score.director.ScoreDirector;

/**
 * 
 * @author Bogdan
 *
 */
public class RelativeActivityPeriodUpdateListener implements VariableListener<Activity> {

	protected void updatePeriod(ScoreDirector scoreDirector, Activity activity) {
		RelativeActivity relativeActivity = activity.getRelativeActivity();
		ActivityPeriod activityPeriod = activity.getActivityPeriod();

		if (relativeActivity != null && activityPeriod != null) {
			/*
			 * if (relativeActivity.getRelativeActivityPeriod() == null) {
			 * scoreDirector.beforeEntityAdded(relativeActivity);
			 * scoreDirector.afterEntityAdded(relativeActivity); }
			 */
			scoreDirector.beforeVariableChanged(relativeActivity, "relativeActivityPeriod");
			relativeActivity.setRelativeActivityPeriod(
					AdjustActivityPeriod.setRelativeActivityPeriod(activityPeriod, relativeActivity.getOffset(),
							activity.getActivityDuration(), relativeActivity.getActivityDuration()));
			scoreDirector.afterVariableChanged(relativeActivity, "relativeActivityPeriod");
		}
	}

	@Override
	public void beforeEntityAdded(ScoreDirector scoreDirector, Activity entity) {
	}

	@Override
	public void afterEntityAdded(ScoreDirector scoreDirector, Activity entity) {
	}

	@Override
	public void beforeVariableChanged(ScoreDirector scoreDirector, Activity entity) {
		updatePeriod(scoreDirector, entity);
	}

	@Override
	public void afterVariableChanged(ScoreDirector scoreDirector, Activity entity) {
		updatePeriod(scoreDirector, entity);
	}

	@Override
	public void beforeEntityRemoved(ScoreDirector scoreDirector, Activity entity) {
	}

	@Override
	public void afterEntityRemoved(ScoreDirector scoreDirector, Activity entity) {
	}

}