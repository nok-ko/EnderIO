package crazypants.enderio.conduits;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import com.enderio.core.common.Lang;
import com.enderio.core.common.util.NNList;

import crazypants.enderio.api.addon.IEnderIOAddon;
import crazypants.enderio.base.config.recipes.RecipeFactory;
import crazypants.enderio.conduits.config.ConfigHandler;
import crazypants.enderio.conduits.init.CommonProxy;
import crazypants.enderio.conduits.network.PacketHandler;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EnderIOConduits.MODID, name = EnderIOConduits.MOD_NAME, version = EnderIOConduits.VERSION, dependencies = EnderIOConduits.DEPENDENCIES)
public class EnderIOConduits implements IEnderIOAddon {

  public static final @Nonnull String MODID = "enderioconduits";
  public static final @Nonnull String DOMAIN = "enderio";
  public static final @Nonnull String MOD_NAME = "Ender IO Conduits";
  public static final @Nonnull String VERSION = "@VERSION@";

  private static final @Nonnull String DEFAULT_DEPENDENCIES = "after:" + crazypants.enderio.base.EnderIO.MODID;
  public static final @Nonnull String DEPENDENCIES = DEFAULT_DEPENDENCIES;

  public static final @Nonnull Lang lang = new Lang(DOMAIN);

  @SidedProxy(clientSide = "crazypants.enderio.conduits.init.ClientProxy", serverSide = "crazypants.enderio.conduits.init.CommonProxy")
  public static CommonProxy proxy;

  @Override
  @Nullable
  public Configuration getConfiguration() {
    return ConfigHandler.config;
  }

  @EventHandler
  public void preInit(@Nonnull FMLPreInitializationEvent event) {
    ConfigHandler.init(event);
    proxy.init(event);
  }

  @EventHandler
  public void init(@Nonnull FMLInitializationEvent event) {
    proxy.init(event);
    PacketHandler.init(event);
  }

  @EventHandler
  public void postInit(@Nonnull FMLPostInitializationEvent event) {
    proxy.init(event);
  }

  @Override
  @Nonnull
  public NNList<Triple<Integer, RecipeFactory, String>> getRecipeFiles() {
    return new NNList<>(Triple.of(2, null, "conduits"));
  }

  @Override
  @Nonnull
  public NNList<String> getExampleFiles() {
    return new NNList<>("conduits_easy_recipes", "conduits_hard_recipes");
  }

}
