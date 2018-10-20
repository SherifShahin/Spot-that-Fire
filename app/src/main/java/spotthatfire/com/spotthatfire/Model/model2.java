package spotthatfire.com.spotthatfire.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class model2 implements Parcelable {

    String id;
    String message;
    boolean notified;
    currentLocation currentLocation;
    fireLocation fireLocation;
    wind wind;


    protected model2(Parcel in) {
        id = in.readString();
        message = in.readString();
        notified = in.readByte() != 0;
    }

    public static final Creator<model2> CREATOR = new Creator<model2>() {
        @Override
        public model2 createFromParcel(Parcel in) {
            return new model2(in);
        }

        @Override
        public model2[] newArray(int size) {
            return new model2[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }


    public spotthatfire.com.spotthatfire.Model.currentLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(spotthatfire.com.spotthatfire.Model.currentLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public spotthatfire.com.spotthatfire.Model.fireLocation getFireLocation() {
        return fireLocation;
    }

    public void setFireLocation(spotthatfire.com.spotthatfire.Model.fireLocation fireLocation) {
        this.fireLocation = fireLocation;
    }

    public spotthatfire.com.spotthatfire.Model.wind getWind()
    {
        return wind;
    }

    public void setWind(spotthatfire.com.spotthatfire.Model.wind wind) {
        this.wind = wind;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(message);
        dest.writeByte((byte) (notified ? 1 : 0));
    }
}
