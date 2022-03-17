package trans.rights.client.modules

abstract class Module(val name: String, val description: String) {
    constructor(name: String) : this(name, "")
}
