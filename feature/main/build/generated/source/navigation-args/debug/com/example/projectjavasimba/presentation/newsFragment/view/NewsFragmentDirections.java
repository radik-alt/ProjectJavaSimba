package com.example.projectjavasimba.presentation.newsFragment.view;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.main.R;
import com.example.projectjavasimba.domain.entity.EventEntity;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class NewsFragmentDirections {
  private NewsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionNewsFragment2ToFilterFragment() {
    return new ActionOnlyNavDirections(R.id.action_newsFragment2_to_filterFragment);
  }

  @NonNull
  public static ActionNewsFragment2ToDetailFragment actionNewsFragment2ToDetailFragment(
      @NonNull EventEntity event) {
    return new ActionNewsFragment2ToDetailFragment(event);
  }

  public static class ActionNewsFragment2ToDetailFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionNewsFragment2ToDetailFragment(@NonNull EventEntity event) {
      if (event == null) {
        throw new IllegalArgumentException("Argument \"event\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("event", event);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionNewsFragment2ToDetailFragment setEvent(@NonNull EventEntity event) {
      if (event == null) {
        throw new IllegalArgumentException("Argument \"event\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("event", event);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("event")) {
        EventEntity event = (EventEntity) arguments.get("event");
        if (Parcelable.class.isAssignableFrom(EventEntity.class) || event == null) {
          __result.putParcelable("event", Parcelable.class.cast(event));
        } else if (Serializable.class.isAssignableFrom(EventEntity.class)) {
          __result.putSerializable("event", Serializable.class.cast(event));
        } else {
          throw new UnsupportedOperationException(EventEntity.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_newsFragment2_to_detailFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public EventEntity getEvent() {
      return (EventEntity) arguments.get("event");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionNewsFragment2ToDetailFragment that = (ActionNewsFragment2ToDetailFragment) object;
      if (arguments.containsKey("event") != that.arguments.containsKey("event")) {
        return false;
      }
      if (getEvent() != null ? !getEvent().equals(that.getEvent()) : that.getEvent() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getEvent() != null ? getEvent().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionNewsFragment2ToDetailFragment(actionId=" + getActionId() + "){"
          + "event=" + getEvent()
          + "}";
    }
  }
}
