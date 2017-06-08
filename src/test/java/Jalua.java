import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;

public class Jalua {
    static Globals globals = JsePlatform.standardGlobals();

    public static void main(String[] args) {
        LuaValue chunk = globals.loadfile("examples/lua/hello.lua");
        chunk.call();
    }

}
