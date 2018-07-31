SUMMARY = "PostGIS is a spatial database extender for PostgreSQL \
 	   object-relational database. It adds support for geographic \
	   objects allowing location queries to be run in SQL."
HOMEPAGE = "https://postgis.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=858853a55d211fc6d66b360eab29de9e"

PR = "r0"

SRC_URI = "\
  https://download.osgeo.org/postgis/source/${PN}-${PV}.tar.gz \
  file://0001-fix-build.patch \
"

SRC_URI[md5sum] = "87608a7f01a50c5bae72a00ba3314e2d"
SRC_URI[sha256sum] = "0663efb589210d5048d95c817e5cf29552ec8180e16d4c6ef56c94255faca8c2"

DEPENDS += "proj json-c libxml2 geos gdal postgresql"
inherit autotools-brokensep pkgconfig gettext

# We rely on postgresql to have a crossscript alternative to the binary pg_config
EXTRA_OECONF = "\
  --with-pgconfig=${STAGING_BINDIR_CROSS}/pg-config \
  --with-gdallibdir=${STAGING_LIBDIR} \
"

CFLAGS  += "-DPOSTGIS_DEBUG_LEVEL=0"

FILES_${PN} += "${libdir}/postgresql/lib/"
FILES_${PN} += "${datadir}"
FILES_${PN} += "${datadir}/postgresql/contrib/postgis-2.4"
FILES_${PN} += "${datadir}/postgresql/extension"
