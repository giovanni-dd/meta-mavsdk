# `meta-mavsdk`

Yocto layer providing a `mavsdk` recipe that mirrors the upstream manual build:

```bash
git clone https://github.com/mavlink/MAVSDK.git
cd MAVSDK
git submodule update --init --recursive
cd cpp
cmake -DCMAKE_BUILD_TYPE=Release -DSUPERBUILD=OFF -Bbuild -S.
cmake --build build -j8
cmake --build build --target install
```

With `SUPERBUILD=OFF`, MAVSDK uses system libraries. This layer supplies the few
packages not available in `meta-oe` (`mavlink`, `libevents`, `picosha2`, `libmavlike`)
and pulls the rest from OpenEmbedded (`libtinyxml2`, `jsoncpp`, `asio`, `fmt`,
`curl`, `xz`, `openssl`).

## Layer dependencies

- `openembedded-core` (poky)
- `meta-oe`

## Build

```bash
bitbake mavsdk
```

## Recipes

| Recipe | Purpose |
| :--- | :--- |
| `mavsdk_git.bb` | MAVSDK C++ library (`SUPERBUILD=OFF`, source in `cpp/`) |
| `mavlink_git.bb` | MAVLink headers (ardupilotmega dialect) |
| `python3-pymavlink-native_git.bb` | Native `mavgen` for mavlink header generation |
| `libevents_git.bb` | MAVLink events library |
| `picosha2_git.bb` | Header-only SHA256 library |
| `libmavlike_git.bb` | Runtime MAVLink message library (`mav` CMake package) |
