package com.allinonefx.controllers;

import com.allinonefx.MainDemo;
import com.allinonefx.datafx.ExtendedAnimatedFlowContainer;
import com.allinonefx.gui.uicomponents.JFoenixController;
import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javax.annotation.PostConstruct;

@ViewController(value = "/fxml/Main.fxml", title = "Material Design Example")
public final class MainController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;

    @FXML
    private StackPane titleBurgerContainer;
    @FXML
    private JFXHamburger titleBurger;

    @FXML
    private StackPane optionsBurger;
    @FXML
    private JFXButton profileButton;
    @FXML
    private JFXRippler optionsRippler;
    @FXML
    private JFXDrawer drawer;
    @FXML
    public static Label lblTitle;

    private JFXPopup toolbarPopup;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws Exception {
        // init the title hamburger icon
        drawer.setOnDrawerOpening(e -> {
            final Transition animation = titleBurger.getAnimation();
            animation.setRate(1);
            animation.play();
        });
        drawer.setOnDrawerClosing(e -> {
            final Transition animation = titleBurger.getAnimation();
            animation.setRate(-1);
            animation.play();
        });
        titleBurgerContainer.setOnMouseClicked(e -> {
            if (drawer.isHidden() || drawer.isHiding()) {
                drawer.open();
            } else {
                drawer.close();
            }
        });

        //set locale
        Locale locale = new Locale("en", "EN");

        //main popup
        ResourceBundle bundle = ResourceBundle.getBundle("lang/message", locale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui/popup/MainPopup.fxml"), bundle);
        loader.setController(new InputController());
        toolbarPopup = new JFXPopup(loader.load());

        optionsBurger.setOnMouseClicked(e -> toolbarPopup.show(optionsBurger,
                PopupVPosition.TOP,
                PopupHPosition.RIGHT,
                -12,
                15));
        profileButton.setOnMouseClicked(e -> toolbarPopup.show(profileButton,
                PopupVPosition.TOP,
                PopupHPosition.RIGHT,
                -12,
                15));

        //set language smatcsv
        ViewConfiguration viewConfig = new ViewConfiguration();
        viewConfig.setResources(ResourceBundle.getBundle("smartcsv", locale));

        // create the inner flow and content
        context = new ViewFlowContext();
        // set the default controller
        Flow innerFlow = new Flow(JFoenixController.class, viewConfig);

        //final FlowHandler flowHandler = innerFlow.createHandler(context);
        FlowHandler flowHandler = new FlowHandler(innerFlow, context, viewConfig);
        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", innerFlow);
        final Duration containerAnimationDuration = Duration.millis(320);
        drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));

        // side controller will add links to the content flow
        Flow sideMenuFlow = new Flow(SideMenuController.class);
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(context);
        drawer.setSidePane(sideMenuFlowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration,
                SWIPE_LEFT)));
    }

    public final class InputController {

        @FXML
        private JFXListView<?> toolbarPopupList;

        // close application
        @FXML
        private void submit() {
            Scene scene = root.getScene();
            final ObservableList<String> stylesheets = scene.getStylesheets();
            if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 0) {
                stylesheets.clear();
                stylesheets.addAll("bootstrapfx.css",
                        MainDemo.class.getResource("/css/jfoenix-fonts.css").toExternalForm(),
                        MainDemo.class.getResource("/css/jfoenix-design.css").toExternalForm(),
                        MainDemo.class.getResource("/css/jfoenix-main-demo.css").toExternalForm());
            } else if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 1) {
                stylesheets.clear();
                stylesheets.addAll("bootstrapfx.css",
                        MainDemo.class.getResource("/css/jfoenix-fonts.css").toExternalForm(),
                        MainDemo.class.getResource("/css/jfoenix-design.css").toExternalForm(),
                        MainDemo.class.getResource("/css/jfoenix-main-demo-red.css").toExternalForm());
            } else if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 3) {
                Platform.exit();
            }
        }
    }
}
