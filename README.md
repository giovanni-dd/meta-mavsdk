# `meta-mavsdk`

Yocto layer providing a `mavsdk` recipe that builds MAVSDK with **superbuild**
enabled (the default upstream behaviour). All third-party dependencies are
fetched and built by MAVSDK's `third_party/` CMake external projects.

This mirrors:

```bash
git clone https://github.com/mavlink/MAVSDK.git
cd MAVSDK
git submodule update --init --recursive
cd cpp
cmake -DCMAKE_BUILD_TYPE=Release -DSUPERBUILD=ON -Bbuild -S.
cmake --build build -j8
cmake --build build --target install
```

## Layer dependencies

- `openembedded-core` (poky)
- `meta-oe` (not required for mavsdk itself, but typically present in the image)

## Build

```bash
bitbake mavsdk
```

Network access is required during `do_configure` and `do_compile` because
superbuild clones dependency repositories from GitHub.

## Recipes

| Recipe | Purpose |
| :--- | :--- |
| `mavsdk_git.bb` | MAVSDK C++ library (`SUPERBUILD=ON`) |
| `python3-pymavlink-native_git.bb` | Native `mavgen` for the mavlink superbuild step |

The only Yocto-specific addition is a patch that makes the mavlink
ExternalProject use `python3-pymavlink-native` instead of `pip install`.
