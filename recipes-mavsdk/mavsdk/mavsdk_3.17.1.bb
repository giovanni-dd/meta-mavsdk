SUMMARY = "MAVSDK C++ library"
DESCRIPTION = "MAVSDK provides a high-level C++ API to communicate with MAVLink systems."
HOMEPAGE = "https://mavsdk.mavlink.io/"
LICENSE = "BSD-3-Clause"

SRC_URI = "file://libmavsdk_3.17.1_armv7.deb"

inherit bin_package

S = "${WORKDIR}"

do_install() {
    install -d ${D}/usr
    cp -a ${S}/usr/* ${D}/usr/
}

FILES:${PN} += "/usr/lib/*"
FILES:${PN}-dev += "/usr/include/* /usr/lib/pkgconfig/*"