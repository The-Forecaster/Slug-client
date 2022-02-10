package trans.rights.client.modules

import trans.rights.client.misc.api.EventPoster

abstract class Module(val name: String, val description: String) : EventPoster
