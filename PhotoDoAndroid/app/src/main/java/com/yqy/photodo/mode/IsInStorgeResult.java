package com.yqy.photodo.mode;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yqy on 2019/11/15.
 */

public class IsInStorgeResult {

    /**
     * image_id : VLE2Yhw6plwPsDgyujMNSg==
     * faces : [{"face_rectangle":{"width":194,"top":103,"left":239,"height":194},"face_token":"6199ed99bdac8755aa8640f6c54b7a47"}]
     * time_used : 630
     * thresholds : {"1e-3":62.327,"1e-5":73.975,"1e-4":69.101}
     * request_id : 1573801485,e7871ecf-10fd-48c6-8596-790cefdd6ac3
     * results : [{"confidence":95.144,"user_id":"","face_token":"fcb04c45f1eb6598df225124cfa393f9"}]
     */

    private String image_id;
    private int time_used;
    private ThresholdsBean thresholds;
    private String request_id;
    private List<FacesBean> faces;
    private List<ResultsBean> results;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public ThresholdsBean getThresholds() {
        return thresholds;
    }

    public void setThresholds(ThresholdsBean thresholds) {
        this.thresholds = thresholds;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public List<FacesBean> getFaces() {
        return faces;
    }

    public void setFaces(List<FacesBean> faces) {
        this.faces = faces;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ThresholdsBean {
        /**
         * 1e-3 : 62.327
         * 1e-5 : 73.975
         * 1e-4 : 69.101
         */

        @SerializedName("1e-3")
        private double _$1e3;
        @SerializedName("1e-5")
        private double _$1e5;
        @SerializedName("1e-4")
        private double _$1e4;

        public double get_$1e3() {
            return _$1e3;
        }

        public void set_$1e3(double _$1e3) {
            this._$1e3 = _$1e3;
        }

        public double get_$1e5() {
            return _$1e5;
        }

        public void set_$1e5(double _$1e5) {
            this._$1e5 = _$1e5;
        }

        public double get_$1e4() {
            return _$1e4;
        }

        public void set_$1e4(double _$1e4) {
            this._$1e4 = _$1e4;
        }
    }

    public static class FacesBean {
        /**
         * face_rectangle : {"width":194,"top":103,"left":239,"height":194}
         * face_token : 6199ed99bdac8755aa8640f6c54b7a47
         */

        private FaceRectangleBean face_rectangle;
        private String face_token;

        public FaceRectangleBean getFace_rectangle() {
            return face_rectangle;
        }

        public void setFace_rectangle(FaceRectangleBean face_rectangle) {
            this.face_rectangle = face_rectangle;
        }

        public String getFace_token() {
            return face_token;
        }

        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }

        public static class FaceRectangleBean {
            /**
             * width : 194
             * top : 103
             * left : 239
             * height : 194
             */

            private int width;
            private int top;
            private int left;
            private int height;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }

    public static class ResultsBean {
        /**
         * confidence : 95.144
         * user_id :
         * face_token : fcb04c45f1eb6598df225124cfa393f9
         */

        private double confidence;
        private String user_id;
        private String face_token;

        public double getConfidence() {
            return confidence;
        }

        public void setConfidence(double confidence) {
            this.confidence = confidence;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getFace_token() {
            return face_token;
        }

        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }
    }
}
