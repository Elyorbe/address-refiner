package me.elyor.addressrefiner.preprocessor;

public interface AddressPreprocessor {

    /**
     * Prepare data before further processing
     * Such as removing unnecessary characters.
     * @return data after preprocessing
     * */
    String preprocess(String rawAddress);

}
