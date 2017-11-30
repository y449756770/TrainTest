LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# LOCAL_MODULE    := libLcbKey
# LOCAL_SRC_FILES := libLcbKey.c

LOCAL_MODULE    := protect1
#VisualGDBAndroid: AutoUpdateSourcesInNextLine
LOCAL_SRC_FILES := protect1.c


LOCAL_LDLIBS := -llog
include $(BUILD_SHARED_LIBRARY)
LOCAL_CFLAGS := -fvisibility=hidden    #���ط��ű�
