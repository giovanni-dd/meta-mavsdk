# `meta-mavsdk`

A Yocto/OpenEmbedded layer providing deterministic and highly-controlled recipes for the third-party dependencies required to build the MAVSDK library.

The primary goal of this layer is to provide **statically linked** versions of critical dependencies, ensuring MAVSDK can be robustly integrated into embedded systems without common shared library conflicts.

-----

## 🚀 Quick Start

To use this layer, you need a functioning Yocto environment (e.g., set up with `poky`).

### 1\. Clone the Layer

Clone this repository into your Yocto build directory (e.g., alongside `poky`, `meta-openembedded`, etc.):

```bash
git clone https://github.com/piotrbetlej/meta-mavsdk.git
```

### 2\. Add the Layer to `bblayers.conf`

Edit your `bblayers.conf` file to include the `meta-mavsdk` layer path:

```bash
# BBLAYERS ?= " \
#   /path/to/poky/meta \
#   /path/to/poky/meta-poky \
#   ...
BBLAYERS ?= " \
  /path/to/poky/meta \
  /path/to/poky/meta-poky \
  /path/to/meta-mavsdk \
  "
```

### 3\. Build Your Target Image

Once integrated, you can include the final `mavsdk` recipe (which would depend on these third-party recipes) in your image:

```bash
# Add MAVSDK to your local.conf or an image recipe
IMAGE_INSTALL:append = " mavsdk"

# Build your image
bitbake core-image-minimal
```

-----

## ✨ Key Features

  * **Static Linking Focus:** All C/C++ library recipes are explicitly configured to build as **static libraries (`.a`)** (e.g., `-DBUILD_SHARED_LIBS=OFF`). This is crucial for MAVSDK's Superbuild-less integration, allowing the final MAVSDK library to bundle its dependencies safely.
  * **Deterministic Builds:** All recipes are pinned to specific Git revision (`SRCREV`) hashes or source tarballs with `SHA256` checksums, guaranteeing reproducible and stable builds.
  * **Security Patches:** Major dependencies like `openssl` and `curl` include critical security patches (e.g., recent CVE fixes) to ensure the integrated dependencies are secure.
  * **Cross-Compilation Ready:** Dependencies like **Protobuf** and **MAVLink** correctly handle the complex requirement of needing **native tools** (e.g., `protoc`, `pymavlink`) to run on the host build machine to generate code for the target.

-----

## ✅ Tested & Proven

We have successfully cross-compiled and tested images utilizing these recipes across diverse architectures, confirming the stability and integrity of the static builds:

| Architecture | Example Target Device | Status |
| :--- | :--- | :--- |
| **x86-64** | Standard PC/Server (QEMU, General PURPOSE) | **PASS** |
| **aarch64** | Radxa Zero 3W (Rockchip RK3566) | **PASS** |
| **armhf** | BeagleBoneBlack | **PASS** |

The recipes are optimized to ensure **low footprint** and **high reliability** for embedded systems like companion computers on drones.

-----

## 📦 Contained Recipes (`recipes-mavsdk/`)

This layer provides tightly-controlled recipes for core MAVSDK dependencies.

| Recipe File | Package | Version / Source Pin | Notes |
| :--- | :--- | :--- | :--- |
| `c-ares-mavsdk-thirdparty_1.27.0.bb` | `c-ares` | `1.27.0` | Asynchronous DNS resolver. Statically built with PIC enabled. |
| `curl-mavsdk-thirdparty_8.7.1.bb` | `curl` | `8.7.1` | Used for HTTP/HTTPS transfers. Includes multiple recent CVE patches. |
| `libjsoncpp-mavsdk-thirdparty_git.bb` | `libjsoncpp` | Git (`89e2973c...`) | C++ library for reading/writing JSON data. |
| `liblzma-mavsdk-thirdparty_5.4.5.bb` | `liblzma` (XZ) | `5.4.5` | Compression library dependency. |
| `mavlink-mavsdk-thirdparty_git.bb` | `mavlink` | Git (`5e3a42b8...`) | MAVLink message headers and generator tooling. Depends on `python3-pymavlink-native`. |
| `openssl-mavsdk-thirdparty_3.2.6.bb` | `openssl` | `3.2.6` | The essential SSL/TLS and cryptographic library. Includes CVE-2024-41996 patch. |
| `picosha2-mavsdk-thirdparty_git.bb` | `picosha2` | Git (`1bf940d8...`) | Header-only SHA-256 implementation. |
| `protobuf-mavsdk-thirdparty_29.1.bb` | `protobuf` | `29.1` | Google's Protocol Buffers. Correctly handles `protoc-native` cross-compilation. |
| `zlib-ng-mavsdk-thirdparty_2.1.6.bb` | `zlib-ng` | `2.1.6` | A next-generation, performance-optimized zlib replacement. |

-----

## 🤝 Dependencies

This layer has dependencies on layers typically found in the OpenEmbedded community:

  * **`poky`**
  * **`meta-oe`**

Please ensure these layers are available and referenced in your `bblayers.conf` before including `meta-mavsdk`.

## 📜 License

All recipes are provided under the MIT License, but the licenses for the software they build are retained from the upstream sources. Please check the `LIC_FILES_CHKSUM` variable within each recipe for specific component licensing information.
