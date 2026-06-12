LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=84b641454775df91a2bae8fdd450e2e9 \
                    file://debian/copyright;md5=40d669a2ad31adadbe5505defc10fbcc"

SRC_URI = "gitsm://github.com/mavlink/MAVSDK.git;protocol=https;branch=main"

FILESEXTRAPATHS:prepend := "${THISDIR}/patches:"
SRC_URI += "file://0001-Disable-superbuild.patch"

SRCREV = "fb2b989b5b9849358bf9c2cb082f496d55edf173"


DEPENDS = " \
    mavlink-mavsdk-thirdparty \
    abseil-cpp-mavsdk-thirdparty \
    libtinyxml2 \
    c-ares-mavsdk-thirdparty \
    protobuf-mavsdk-thirdparty \
    curl-mavsdk-thirdparty \
    zlib-mavsdk-thirdparty \
    picosha2-mavsdk-thirdparty \
    re2-mavsdk-thirdparty \
    libevents-mavsdk-thirdparty \
    grpc-mavsdk-thirdparty \
    googletest-mavsdk-thirdparty \
    openssl-mavsdk-thirdparty \
    libjsoncpp-mavsdk-thirdparty \
    libmavlike-mavsdk-thirdparty \
    liblzma-mavsdk-thirdparty \
"

inherit cmake

EXTRA_OECMAKE = "-DSUPERBUILD:BOOL=OFF -DDEPS_INSTALL_PATH:STRING=${RECIPE_SYSROOT}/usr"
