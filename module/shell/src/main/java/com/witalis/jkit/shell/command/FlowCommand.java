package com.witalis.jkit.shell.command;

import lombok.Getter;
import org.jline.terminal.Terminal;
import org.springframework.core.io.ResourceLoader;
import org.springframework.shell.component.SingleItemSelector;
import org.springframework.shell.component.SingleItemSelector.SingleItemSelectorContext;
import org.springframework.shell.component.support.SelectorItem;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.style.TemplateExecutor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.witalis.jkit.shell.utils.Constants.GROUP;

@Getter
@ShellComponent
public class FlowCommand {
    private final Terminal terminal;
    private final TemplateExecutor templateExecutor;
    private final ResourceLoader resourceLoader;

    public FlowCommand(
        final Terminal terminal,
        final TemplateExecutor templateExecutor,
        final ResourceLoader resourceLoader
    ) {
        this.terminal = terminal;
        this.templateExecutor = templateExecutor;
        this.resourceLoader = resourceLoader;
    }

    enum Options {
        OPTION1("The 1st option"),
        OPTION2("The 2nd option"),
        OPTION3("The 3rd option");

        private final String description;

        Options(final String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    @ShellMethod(
        key = "choice",
        value = "Single Selector",
        group = GROUP
    )
    public String singleSelector() {
        final SelectorItem<String> item1 = SelectorItem.of(Options.OPTION1.name(), Options.OPTION1.getDescription());
        final SelectorItem<String> item2 = SelectorItem.of(Options.OPTION2.name(), Options.OPTION2.getDescription());
        final SelectorItem<String> item3 = SelectorItem.of(Options.OPTION3.name(), Options.OPTION3.getDescription());

        final List<SelectorItem<String>> items = Arrays.asList(item1, item2, item3);
        final SingleItemSelector<String, SelectorItem<String>> component = new SingleItemSelector<>(
            getTerminal(), items, "Item selection", null
        );
        component.setResourceLoader(getResourceLoader());
        component.setTemplateExecutor(getTemplateExecutor());

        final SingleItemSelectorContext<String, SelectorItem<String>> context = component.run(SingleItemSelectorContext.empty());
        String selection = context.getResultItem().flatMap(item -> Optional.ofNullable(item.getItem())).orElse("Undefined");

        return "Selection: " + selection;
    }
}
