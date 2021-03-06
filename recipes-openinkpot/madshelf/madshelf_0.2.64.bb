DESCRIPTION = "Bookshelf application"
SECTION = "x11/application"
DEPENDS = "ecore evas edje efreet liblanguage liblops libkeys libeoi xcb-util-wm libextractor-mini"
HOMEPAGE = "http://www.openinkpot.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=393a5ca445f6965873eca0259a17f833"

SRC_URI = "git://git.openinkpot.org/madshelf.git;protocol=git \
			file://x.patch"

SRCREV = ""

S = "${WORKDIR}/git/"

PR = "r1"

inherit autotools gettext

do_install_append() {
	#rm -rf ${D}/usr/lib/madshelf/positions/.debug
	rm -rf ${D}/usr/lib/madshelf/positions/*.a

	mkdir -p ${D}/etc/madshelf
	install -m 0644 ${S}/debian/disks.conf ${D}/etc/madshelf

	mkdir -p ${D}/etc/keys
	install -m 0644 ${S}/debian/n516-keys.ini ${D}/etc/keys/madshelf.ini
	mkdir -p ${D}etc/madshelf
	install -m 0644 ${S}/debian/n516-prefs.conf ${D}/etc/madshelf/prefs.conf

	#mkdir -p ${D}/etc/keys
	#install -m 0644 ${S}/debian/prs505-keys.ini ${D}/etc/keys/madshelf.ini
}

# TODO: add separate package with data

PACKAGES = "${PN}-dbg ${PN}"
FILES_${PN} += " /usr/share /etc/keys /etc/madshelf"
FILES_${PN}-dbg += " /usr/lib/madshelf/positions/.debug usr/lib/madshelf/positions/positions_*.a"
