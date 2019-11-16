package com.yqy.photodo.mode;

import java.util.List;

/**
 * Created by yqy on 2019/11/12.
 */

public class DetectPhotoResult {

    /**
     * time_used : 350
     * faces : [{"attributes":{"gender":{"value":"Male"},"age":{"value":34}},"face_rectangle":{"width":49,"top":194,"left":165,"height":49},"face_token":"2717d02a20c4e1ac19199ca1a3805336"},{"attributes":{"gender":{"value":"Female"},"age":{"value":39}},"face_rectangle":{"width":48,"top":56,"left":168,"height":48},"face_token":"9943c0006e0f26e1fd123da99e93186b"},{"attributes":{"gender":{"value":"Female"},"age":{"value":40}},"face_rectangle":{"width":47,"top":194,"left":261,"height":47},"face_token":"2d1dd60716a587e51e41a5347052c8d6"},{"attributes":{"gender":{"value":"Male"},"age":{"value":39}},"face_rectangle":{"width":46,"top":60,"left":263,"height":46},"face_token":"fde6fb73d8982ab97d7a22b193c88a2e"}]
     * image_id : DJMd/Lm7uBnMia7UY3VLdA==
     * request_id : 1573543240,ccf0cfb3-fb34-4a47-a477-002a599091f1
     * face_num : 4
     */

    private int time_used;
    private String image_id;
    private String request_id;
    private int face_num;
    private List<FacesBean> faces;

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getFace_num() {
        return face_num;
    }

    public void setFace_num(int face_num) {
        this.face_num = face_num;
    }

    public List<FacesBean> getFaces() {
        return faces;
    }

    public void setFaces(List<FacesBean> faces) {
        this.faces = faces;
    }

    public static class FacesBean {
        /**
         * attributes : {"gender":{"value":"Male"},"age":{"value":34}}
         * face_rectangle : {"width":49,"top":194,"left":165,"height":49}
         * face_token : 2717d02a20c4e1ac19199ca1a3805336
         */

        private AttributesBean attributes;
        private FaceRectangleBean face_rectangle;
        private String face_token;

        public AttributesBean getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesBean attributes) {
            this.attributes = attributes;
        }

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

        public static class AttributesBean {
            /**
             * gender : {"value":"Male"}
             * age : {"value":34}
             */

            private GenderBean gender;
            private AgeBean age;

            public GenderBean getGender() {
                return gender;
            }

            public void setGender(GenderBean gender) {
                this.gender = gender;
            }

            public AgeBean getAge() {
                return age;
            }

            public void setAge(AgeBean age) {
                this.age = age;
            }

            public static class GenderBean {
                /**
                 * value : Male
                 */

                private String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class AgeBean {
                /**
                 * value : 34
                 */

                private int value;

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }
        }

        public static class FaceRectangleBean {
            /**
             * width : 49
             * top : 194
             * left : 165
             * height : 49
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
}
