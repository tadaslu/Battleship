//package tadas.luksas;
//
//import com.vaadin.shared.ui.ContentMode;
//import com.vaadin.ui.*;
//
//public class BattleField extends CustomsComponents {
//    Layout mainLayout;
//
//
//    public BattleField(GameTable gameTable) {
//        mainLayout = new GridLayout(11, 11);
//        this.setCompositionRoot(mainLayout);
//
//        updateData(gameTable.getGameTable());
//    }
//
//    public void updateData(GameTable gameTable){
//        mainLayout.removeAllComponents();
//        addHeader();
//        for(int i = 0; i < gameData.length; i++){
//            addRow(i, gameData[i]);
//        }
//    }
//    private void addHeader(){
//        mainLayout.addComponent(new Label(""));
//        for(int i=0; i< GameTable.COLUMNS.length; i++){
//            mainLayout.addComponent(new Label(GameTable.COLUMNS[i]));
//        }
//    }
//    private void addRow (int index, String[] arr){
//        if(index >- 0){
//            mainLayout.addComponent(new Label(index + ""));
//        }else{
//            mainLayout.addComponent(new Label("&nbsp;", ContentMode.HTML));
//        }
//        for(int i=0; i< arr.length; i++){
//            final int idx = 1;
//            String buttonLable = ("".equals(arr[i])?"&nbsp;":arr[i]);
//            Button button = new Button(buttonLable);
//            mainLayout.addComponent(button);
//            button.addClickListener(new Button.ClickListener() {
//                @Override
//                public void buttonClick(Button.ClickEvent clickEvent) {
//                    System.out.println(LocalGameService.getColumnAsString(index)+" "+idx);
//
//                }
//            });
//        }
//
//    }
//
//}
