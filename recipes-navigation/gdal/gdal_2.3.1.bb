DESCRIPTION = "GDAL is a translator library for raster geospatial data formats"
HOMEPAGE = "http://www.gdal.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=65b35c879d7e99557086d68a48c385ea"

DEPENDS = "proj libpng tiff zlib postgresql"

SRC_URI = "http://download.osgeo.org/${PN}/${PV}/${P}.tar.xz"

SRC_URI[md5sum] = "6b50f4da73a12a9b69ef14054ea2afcd"
SRC_URI[sha256sum] = "9c4625c45a3ee7e49a604ef221778983dd9fd8104922a87f20b99d9bedb7725a"

inherit autotools-brokensep binconfig

EXTRA_OECONF = "--without-grass \
                --without-libgrass \
                --without-cfitsio \
                --without-dds \
                --without-gta \
                --without-pcidsk \
                --without-ogdi \
                --without-fme \
                --without-hdf4 \
                --without-hdf5 \
                --without-jpeg12 \
                --without-ogdi \
                --without-netcdf \
                --without-openjpeg \
                --without-fgdb \
                --without-ecw \
                --without-kakadu \
                --without-mrsid \
                --without-jp2mrsid \
                --without-mrsid_lidar \
                --without-msg \
                --without-bsb \
                --without-grib \
                --without-mysql \
                --without-ingres \
                --without-odbc \
                --without-dods_root \
                --without-spatialite \
                --without-pcre \
                --without-idb \
                --without-sde \
                --without-sde-version \
                --without-epsilon \
                --without-webp \
                --without-opencl \
                --without-opencl-include \
                --without-opencl-lib \
                --without-freexl \
                --without-pam \
                --without-poppler \
                --without-podofo \
                --without-podofo-lib \
                --without-podofo-extra-lib-for-test \
                --without-perl \
                --without-php \
                --without-python \
                --without-java \
                --without-mdb \
                --without-jvm-lib \
                --without-jvm-lib-add-rpath \
                --without-rasdaman \
                --without-armadillo \
                --without-sqlite3 \
                \
                --with-pcraster=internal \
                --with-geotiff=internal \ 
"

EXTRA_OEMAKE += " GNUmakefile"

PACKAGECONFIG ?= "geos png jasper z"
PACKAGECONFIG[geos] = "--with-geos,--without-geos,geos"
PACKAGECONFIG[lzma] = "--with-liblzma,--without-liblzma,xz"
PACKAGECONFIG[png] = "--with-png,--without-png,libpng"
PACKAGECONFIG[gif] = "--with-gif,--without-gif,giflib"
PACKAGECONFIG[jpeg] = "--with-jpeg,--without-jpeg,jpeg"
PACKAGECONFIG[z] = "--with-libz,--without-libz,zlib"
PACKAGECONFIG[jasper] = "--with-jasper,--without-jasper,jasper"
PACKAGECONFIG[curl] = "--with-curl,--without-curl,curl"

FILES_${PN} += "${libdir}/gdalplugins"
FILES_${PN} += "${datadir}/"

