package hu.unideb.inf.mvpsqlroom.interfaces;

import hu.unideb.inf.mvpsqlroom.models.ResultClass;

public interface IMainActivityView {
    void refreshUiWithMessage(String message);
    void refreshUiWithObject(ResultClass<Object> result);
}
