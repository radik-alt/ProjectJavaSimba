package com.example.projectjavasimba.presentation.detailNewsFragment.view;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import com.example.projectjavasimba.domain.entity.EventEntity;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class DetailFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private DetailFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private DetailFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings({
      "unchecked",
      "deprecation"
  })
  public static DetailFragmentArgs fromBundle(@NonNull Bundle bundle) {
    DetailFragmentArgs __result = new DetailFragmentArgs();
    bundle.setClassLoader(DetailFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("event")) {
      EventEntity event;
      if (Parcelable.class.isAssignableFrom(EventEntity.class) || Serializable.class.isAssignableFrom(EventEntity.class)) {
        event = (EventEntity) bundle.get("event");
      } else {
        throw new UnsupportedOperationException(EventEntity.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (event == null) {
        throw new IllegalArgumentException("Argument \"event\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("event", event);
    } else {
      throw new IllegalArgumentException("Required argument \"event\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static DetailFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    DetailFragmentArgs __result = new DetailFragmentArgs();
    if (savedStateHandle.contains("event")) {
      EventEntity event;
      event = savedStateHandle.get("event");
      if (event == null) {
        throw new IllegalArgumentException("Argument \"event\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("event", event);
    } else {
      throw new IllegalArgumentException("Required argument \"event\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public EventEntity getEvent() {
    return (EventEntity) arguments.get("event");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("event")) {
      EventEntity event = (EventEntity) arguments.get("event");
      if (Parcelable.class.isAssignableFrom(EventEntity.class) || event == null) {
        __result.set("event", Parcelable.class.cast(event));
      } else if (Serializable.class.isAssignableFrom(EventEntity.class)) {
        __result.set("event", Serializable.class.cast(event));
      } else {
        throw new UnsupportedOperationException(EventEntity.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    DetailFragmentArgs that = (DetailFragmentArgs) object;
    if (arguments.containsKey("event") != that.arguments.containsKey("event")) {
      return false;
    }
    if (getEvent() != null ? !getEvent().equals(that.getEvent()) : that.getEvent() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getEvent() != null ? getEvent().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "DetailFragmentArgs{"
        + "event=" + getEvent()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull DetailFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull EventEntity event) {
      if (event == null) {
        throw new IllegalArgumentException("Argument \"event\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("event", event);
    }

    @NonNull
    public DetailFragmentArgs build() {
      DetailFragmentArgs result = new DetailFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setEvent(@NonNull EventEntity event) {
      if (event == null) {
        throw new IllegalArgumentException("Argument \"event\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("event", event);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public EventEntity getEvent() {
      return (EventEntity) arguments.get("event");
    }
  }
}
