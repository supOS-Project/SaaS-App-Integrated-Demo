package com.bluetron.eco.sdk.dto.org;

import java.util.List;

public class PersonRes {
    private String code;
    private String name;
    private Integer valid;
    private PersonRes.Gender gender;
    private PersonRes.Status status;
    private PersonRes.User user;
    private PersonRes.MainPosition mainPosition;
    private List<PositionRes> positionRes;
    private String modifyTime;

    public static PersonRes.PersonResBuilder builder() {
        return new PersonRes.PersonResBuilder();
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public Integer getValid() {
        return this.valid;
    }

    public PersonRes.Gender getGender() {
        return this.gender;
    }

    public PersonRes.Status getStatus() {
        return this.status;
    }

    public PersonRes.User getUser() {
        return this.user;
    }

    public PersonRes.MainPosition getMainPosition() {
        return this.mainPosition;
    }

    public List<PositionRes> getPositionRes() {
        return this.positionRes;
    }

    public String getModifyTime() {
        return this.modifyTime;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setValid(final Integer valid) {
        this.valid = valid;
    }

    public void setGender(final PersonRes.Gender gender) {
        this.gender = gender;
    }

    public void setStatus(final PersonRes.Status status) {
        this.status = status;
    }

    public void setUser(final PersonRes.User user) {
        this.user = user;
    }

    public void setMainPosition(final PersonRes.MainPosition mainPosition) {
        this.mainPosition = mainPosition;
    }

    public void setPositionRes(final List<PositionRes> positionRes) {
        this.positionRes = positionRes;
    }

    public void setModifyTime(final String modifyTime) {
        this.modifyTime = modifyTime;
    }


    @Override
    public String toString() {
        return "PersonRes(code=" + this.getCode() + ", name=" + this.getName() + ", valid=" + this.getValid() + ", gender=" + this.getGender() + ", status=" + this.getStatus() + ", user=" + this.getUser() + ", mainPosition=" + this.getMainPosition() + ", positionRes=" + this.getPositionRes() + ", modifyTime=" + this.getModifyTime() + ")";
    }

    public PersonRes() {
    }

    public PersonRes(final String code, final String name, final Integer valid, final PersonRes.Gender gender, final PersonRes.Status status, final PersonRes.User user, final PersonRes.MainPosition mainPosition, final List<PositionRes> positionRes, final String modifyTime) {
        this.code = code;
        this.name = name;
        this.valid = valid;
        this.gender = gender;
        this.status = status;
        this.user = user;
        this.mainPosition = mainPosition;
        this.positionRes = positionRes;
        this.modifyTime = modifyTime;
    }

    public static class PersonResBuilder {
        private String code;
        private String name;
        private Integer valid;
        private PersonRes.Gender gender;
        private PersonRes.Status status;
        private PersonRes.User user;
        private PersonRes.MainPosition mainPosition;
        private List<PositionRes> positionRes;
        private String modifyTime;

        PersonResBuilder() {
        }

        public PersonRes.PersonResBuilder code(final String code) {
            this.code = code;
            return this;
        }

        public PersonRes.PersonResBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public PersonRes.PersonResBuilder valid(final Integer valid) {
            this.valid = valid;
            return this;
        }

        public PersonRes.PersonResBuilder gender(final PersonRes.Gender gender) {
            this.gender = gender;
            return this;
        }

        public PersonRes.PersonResBuilder status(final PersonRes.Status status) {
            this.status = status;
            return this;
        }

        public PersonRes.PersonResBuilder user(final PersonRes.User user) {
            this.user = user;
            return this;
        }

        public PersonRes.PersonResBuilder mainPosition(final PersonRes.MainPosition mainPosition) {
            this.mainPosition = mainPosition;
            return this;
        }

        public PersonRes.PersonResBuilder positionRes(final List<PositionRes> positionRes) {
            this.positionRes = positionRes;
            return this;
        }

        public PersonRes.PersonResBuilder modifyTime(final String modifyTime) {
            this.modifyTime = modifyTime;
            return this;
        }

        public PersonRes build() {
            return new PersonRes(this.code, this.name, this.valid, this.gender, this.status, this.user, this.mainPosition, this.positionRes, this.modifyTime);
        }

        @Override
        public String toString() {
            return "PersonRes.PersonResBuilder(code=" + this.code + ", name=" + this.name + ", valid=" + this.valid + ", gender=" + this.gender + ", status=" + this.status + ", user=" + this.user + ", mainPosition=" + this.mainPosition + ", positionRes=" + this.positionRes + ", modifyTime=" + this.modifyTime + ")";
        }
    }

    private static class MainPosition {
        private String code;
        private String name;

        public MainPosition() {
        }

        public String getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }

        public void setCode(final String code) {
            this.code = code;
        }

        public void setName(final String name) {
            this.name = name;
        }



        @Override
        public String toString() {
            return "PersonRes.MainPosition(code=" + this.getCode() + ", name=" + this.getName() + ")";
        }
    }

    private static class User {
        private String username;

        public User() {
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(final String username) {
            this.username = username;
        }



        @Override
        public String toString() {
            return "PersonRes.User(username=" + this.getUsername() + ")";
        }
    }

    private static class Status {
        private String code;
        private String name;

        public Status() {
        }

        public String getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }

        public void setCode(final String code) {
            this.code = code;
        }

        public void setName(final String name) {
            this.name = name;
        }



        @Override
        public String toString() {
            return "PersonRes.Status(code=" + this.getCode() + ", name=" + this.getName() + ")";
        }
    }

    private static class Gender {
        private String code;
        private String name;

        public Gender() {
        }

        public String getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }

        public void setCode(final String code) {
            this.code = code;
        }

        public void setName(final String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return "PersonRes.Gender(code=" + this.getCode() + ", name=" + this.getName() + ")";
        }
    }
}
