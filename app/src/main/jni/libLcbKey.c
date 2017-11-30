#include "com_example_incomplete_trainingtest_NDKJniUtiles.h"
#include <assert.h>
#include <jni.h>
#include "string.h"
#include <sys/mman.h>
#include <Android/log.h>
#include <stdlib.h>
#include <stdio.h>
#include <elf.h>
#include  <sys/types.h>
#include <unistd.h>
#include <sys/ptrace.h>

#include "com_example_incomplete_trainingtest_NDKJniUtiles.h"

#define JNIREG_CLASS "com/example/incomplete/trainingtest/NDKJniUtiles"

//jstring Java_com_ab_baseabbx_jni_LcbJNI_getSpKey(JNIEnv* env, jobject thiz) {
//	const char key[] = {'A','n','B','a','n','g','.','c','o','m', '\0'};
//	return (*env)->NewStringUTF(env, key);
//}
//
//jstring Java_com_ab_baseabbx_jni_LcbJNI_getLcbKey(JNIEnv* env, jobject thiz) {
//	//char *key = "7f25a689wf3guDwwCkt56qVO660928bO";
////	const char s[] = {'7','f','2','5','a','6','8','9','w','f','3','g','u','D','w','w','C','k','t','5','6','q','V','O','6','6','0','9','2','8','b','O','\0'};
//	const char s[] = {'a', 'n', 'b', 'a', 'n', 'g', 'j', 'r', '\0'};
//	return (*env)->NewStringUTF(env, s);
//}
//
//jstring Java_com_ab_baseabbx_jni_LcbJNI_getLcbKey203(JNIEnv* env, jobject thiz) {
//	//char *key = "7f25a689wf3guDwwCkt56qVO660928bO";
//	//const char s[] = {'7','f','2','5','a','6','8','9','w','f','3','g','u','D','w','w','C','k','t','5','6','q','V','O','6','6','0','9','2','8','b','O','\0'};
//	const char s[] = {'7','f','2','5','a','6','8','9','w','\0'};
//	return (*env)->NewStringUTF(env, s);
//}



//void *getStringc(JNIEnv *env, jobject obj, jstring str);
//
//static JNINativeMethod gMethods[] = {
//        {"getJniString", "(Ljava/lang/String;)Ljava/lang/String;", (void *) getStringc},
//
//};
//
//static int registerNativeMethods(JNIEnv *env, const char *className,
//                                 JNINativeMethod *gMethods, int numMethods) {
//    jclass clazz;
//    clazz = (*env)->FindClass(env, className);
//    if (clazz == NULL) {
//        return JNI_FALSE;
//    }
//    if ((*env)->RegisterNatives(env, clazz, gMethods, numMethods) < 0) {
//        return JNI_FALSE;
//    }
//
//    return JNI_TRUE;
//}
//
//static int registerNatives(JNIEnv *env) {
//    if (!registerNativeMethods(env, JNIREG_CLASS, gMethods,
//                               sizeof(gMethods) / sizeof(gMethods[0])))
//        return JNI_FALSE;
//
//    return JNI_TRUE;
//}
//
//void anti_debug() {
//    ptrace(PTRACE_TRACEME, 0, 0, 0);
//}
//
//jint JNI_OnLoad(JavaVM *vm, void *reserved) {
//    //anti_debug();
//    JNIEnv *env;
//    if ((*vm)->GetEnv(vm, (void **) (&env), JNI_VERSION_1_6) != JNI_OK) {
//        return -1;
//    }
//    assert(env != NULL);
//
//    if (!registerNatives(env)) {//注册
//        return -1;
//    }
//
//    return JNI_VERSION_1_6;
//}
//
////jstring JNICALL Java_com_example_zbb_test01_MainActivity_getStringFromNative(JNIEnv *env, jobject obj, jstring str) __attribute__((section(".mytext")));
////JNIEXPORT jstring JNICALL Java_com_example_zbb_test01_MainActivity_getStringFromNative
//__attribute__((section (".mytext")))  JNICALL jstring getStringc
//        (JNIEnv *env, jobject obj, jstring str) {
//    // jstring   CharTojstring(JNIEnv* env,   char* str);
//    //首先将string类型的转化为char类型的字符串
//    const char *strAry = (*env)->GetStringUTFChars(env, str, 0);
//    if (strAry == NULL) {
//        return NULL;
//    }
//    int len = strlen(strAry);
//    char *last = (char *) malloc((len + 1) * sizeof(char));
//    memset(last, 0, len + 1);
//    //char buf[]={'z','h','a','o','b','e','i','b','e','i'};
//    char *buf = "HelloJni－retunFromJNI";
//    int buf_len = strlen(buf);
//    int i;
//    for (i = 0; i < len; i++) {
//        last[i] = strAry[i] | buf[i % buf_len];
//        if (last[i] == 0) {
//            last[i] = strAry[i];
//        }
//    }
//    last[len] = 0;
//    return (*env)->NewStringUTF(env, buf);
//}

jstring getJniString(JNIEnv *) __attribute__((section (".mytext")));

jstring getJniString(JNIEnv *env) {
    return (*env)->NewStringUTF(env, "Native method return!");
};

void init_getJniString() __attribute__((constructor));

unsigned long getLibAddr();

void init_getJniString() {
    char name[15];
    unsigned int nblock;
    unsigned int nsize;
    unsigned long base;
    unsigned long text_addr;
    unsigned int i;
    Elf32_Ehdr *ehdr;
    Elf32_Shdr *shdr;

    base = getLibAddr();

    ehdr = (Elf32_Ehdr *) base;
    text_addr = ehdr->e_shoff + base;

    nblock = ehdr->e_entry >> 16;
    nsize = ehdr->e_entry & 0xffff;

    __android_log_print(ANDROID_LOG_INFO, "JNITag", "nblock =  0x%x,nsize:%d", nblock, nsize);
    __android_log_print(ANDROID_LOG_INFO, "JNITag", "base =  0x%x", text_addr);
    printf("nblock = %d\n", nblock);

    if (mprotect((void *) (text_addr / PAGE_SIZE * PAGE_SIZE), 4096 * nsize,
                 PROT_READ | PROT_EXEC | PROT_WRITE) != 0) {
        puts("mem privilege change failed");
        __android_log_print(ANDROID_LOG_INFO, "JNITag", "mem privilege change failed");
    }

    for (i = 0; i < nblock; i++) {
        char *addr = (char *) (text_addr + i);
        *addr = ~(*addr);
    }

    if (mprotect((void *) (text_addr / PAGE_SIZE * PAGE_SIZE), 4096 * nsize,
                 PROT_READ | PROT_EXEC) != 0) {
        puts("mem privilege change failed");
    }
    puts("Decrypt success");
}

unsigned long getLibAddr() {
    unsigned long ret = 0;
    char name[] = "libLcbKey.so";
    char buf[4096], *temp;
    int pid;
    FILE *fp;
    pid = getpid();
    sprintf(buf, "/proc/%d/maps", pid);
    fp = fopen(buf, "r");
    if (fp == NULL) {
        puts("open failed");
        goto _error;
    }
    while (fgets(buf, sizeof(buf), fp)) {
        if (strstr(buf, name)) {
            temp = strtok(buf, "-");
            ret = strtoul(temp, NULL, 16);
            break;
        }
    }
    _error:
    fclose(fp);
    return ret;
}


JNIEXPORT jstring JNICALL
Java_com_example_incomplete_trainingtest_NDKJniUtiles_getJniString(JNIEnv *env, jclass type) {

    // TODO
//#if defined(__arm__)
//#if defined(__ARM_ARCH_7A__)
//#if defined(__ARM_NEON__)
//#define ABI "armeabi-v7a/NEON"
//#else
//#define ABI "armeabi-v7a"
//#endif
//#else
//#define ABI "armeabi"
//#endif
//#elif defined(__i386__)
//#define ABI "x86"
//#elif defined(__mips__)
//#define ABI "mips"
//#else
//#define ABI "unknown"
//#endif
//    return (*env)->NewStringUTF(env, "Native method return!");
    return getJniString(env);
}