package hu.unideb.inf.mvpsqlroom.models;

public class ResultClass<T> {
    private T resultModel;

    public T getResultModel() {
        return resultModel;
    }

    public void setResultModel(T resultModel) {
        this.resultModel = resultModel;
    }

    @Override
    public String toString() {
        return "ResultClass{" +
                "resultModel=" + resultModel.toString() +
                '}';
    }
}
