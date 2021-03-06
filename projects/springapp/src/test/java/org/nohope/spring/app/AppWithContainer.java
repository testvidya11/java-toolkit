package org.nohope.spring.app;

import org.springframework.context.ConfigurableApplicationContext;
import org.nohope.spring.app.module.IModule;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author <a href="mailto:ketoth.xupack@gmail.com">ketoth xupack</a>
 * @since 7/27/12 5:33 PM
 */
class AppWithContainer extends Handler<IModule> {
    private final List<IModule> modules = new ArrayList<>();

    @Override
    protected void onModuleCreated(@Nonnull final IModule module,
                                   @Nonnull final ConfigurableApplicationContext ctx,
                                   @Nonnull final Properties properties,
                                   @Nonnull final String name) {
        super.onModuleCreated(module, ctx, properties, name);
        modules.add(module);
    }

    @Override
    protected void onModuleDiscovered(@Nonnull final Class<? extends IModule> clazz,
                                      @Nonnull final ConfigurableApplicationContext ctx,
                                      @Nonnull final Properties properties,
                                      @Nonnull final String name) {
        super.onModuleDiscovered(clazz, ctx, properties, name);
        ctx.getBeanFactory().registerSingleton("name", name);
        ctx.getBeanFactory().registerSingleton("properties", properties);
        ctx.getBeanFactory().registerSingleton("ctx", ctx);
    }

    public ConfigurableApplicationContext getContext() {
        return getAppContext();
    }

    public List<IModule> getModules() {
        return modules;
    }
}
