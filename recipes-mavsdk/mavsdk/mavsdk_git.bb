SUMMARY = "MAVSDK C++ library"
DESCRIPTION = "MAVSDK provides a high-level C++ API to communicate with MAVLink systems."
HOMEPAGE = "https://mavsdk.mavlink.io/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=84b641454775df91a2bae8fdd450e2e9 \
                    file://debian/copyright;md5=40d669a2ad31adadbe5505defc10fbcc"

FILESEXTRAPATHS:prepend := "${THISDIR}:${THISDIR}/patches:"

SRC_URI = "gitsm://github.com/mavlink/MAVSDK.git;protocol=https;branch=main"
SRC_URI += "file://0001-yocto-superbuild-mavlink.patch"

MAVLINK_YOCTO_PYMAVLINK_PATCH := "${THISDIR}/mavlink-yocto-pymavlink.patch"

PV = "3.11+git"
SRCREV = "33b23ddaa68ae2b124754376346b44b3f0f15685"

OECMAKE_SOURCEPATH = "${S}/cpp"

DEPENDS = " \
    cmake-native \
    ninja-native \
    git-native \
    perl-native \
    python3-native \
    python3-pymavlink-native \
    python3-future-native \
    python3-lxml-native \
    pkgconfig-native \
"

inherit cmake python3native pkgconfig

EXTRA_OECMAKE = " \
    -DSUPERBUILD=ON \
    -DBUILD_TESTING=OFF \
    -DBUILD_MAVSDK_SERVER=OFF \
    -DPYTHON_NATIVE_SITEPACKAGES=${STAGING_LIBDIR_NATIVE}/python${PYTHON_BASEVERSION}/site-packages \
    -DPython3_EXECUTABLE=${PYTHON} \
"

# Superbuild fetches and builds third-party deps via ExternalProject during cmake configure.
do_configure[network] = "1"
do_compile[network] = "1"

do_patch:append() {
    bb.build.exec_func('mavsdk_install_mavlink_patch', d)
}

mavsdk_install_mavlink_patch() {
    install -m 0644 ${MAVLINK_YOCTO_PYMAVLINK_PATCH} ${S}/cpp/third_party/mavlink/mavlink-yocto-pymavlink.patch
}
