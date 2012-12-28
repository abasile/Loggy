LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := Loggy
LOCAL_SRC_FILES := Loggy.cpp

include $(BUILD_SHARED_LIBRARY)
