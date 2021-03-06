# This recipe tracks the 'bleeding edge' linux-xlnx repository.
# Since this tree is frequently updated, AUTOREV is used to track its contents.
#
# To enable this recipe, set PREFERRED_PROVIDER_virtual/kernel = "linux-xlnx-dev"

KBRANCH ?= "master"
SRCBRANCH = "${KBRANCH}"

# Use the SRCREV for the last tagged revision of linux-xlnx.
SRCREV ?= '${@oe.utils.conditional("PREFERRED_PROVIDER_virtual/kernel", "linux-xlnx-dev", "${AUTOREV}", "629041605b93343ad2e8971ceaac3edcef0b043b", d)}'

python () {
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel", True) != "linux-xlnx-dev":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-xlnx-dev to enable it")
}

LINUX_VERSION ?= "4.6+"
LINUX_VERSION_EXTENSION ?= "-xilinx-dev"
PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

include linux-xlnx.inc

