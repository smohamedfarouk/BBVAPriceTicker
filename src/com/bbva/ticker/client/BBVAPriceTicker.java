package com.bbva.ticker.client;

/**
 * Created by moham on 28/01/2016.
 */

import com.bbva.ticker.model.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class BBVAPriceTicker implements Client{
    private Map<String, Component> components = new HashMap<>();
    private Map<String, String> currencySubscriptions = new HashMap<>();

    final PricingServiceClient client = new PricingServiceClient(this);
    private Throwable m_throwable;
    private JButton setHistoryRequested(String currency, String source) {
        JButton m_history_required = new JButton();
        String name = "historyReq" + currency + "_" + source;
        m_history_required.setName(name);
        components.put(name, m_history_required);
        m_history_required.setVisible(false);
        return m_history_required;
    }

    private JButton getSubscribeButton(String currency, String source) {
        String name = "subscribe_button_" + currency + "_" + source;
        JButton m_subscribeButton = null;
        m_subscribeButton = (JButton)components.get(name);
        if (m_subscribeButton==null) {
             m_subscribeButton = new JButton("Subscribe");
            m_subscribeButton.setName(name);
            m_subscribeButton.addActionListener(subscriberActionListener);
            components.put(name, m_subscribeButton);
            m_subscribeButton.setEnabled(true);

        }
        m_subscribeButton =(JButton)components.get(name);
        return m_subscribeButton;
    }

    private JButton getUnsubscribeButton(String currency, String source) {
        String name = "unsubscribe_button_" + currency + "_" + source;
        JButton m_unsubscribeButton = null;
        m_unsubscribeButton = (JButton)components.get(name);
        if (m_unsubscribeButton==null) {
            m_unsubscribeButton = new JButton("Unsubscribe");
            m_unsubscribeButton.setName(name);
            components.put(name, m_unsubscribeButton);
            m_unsubscribeButton.addActionListener(unSubscriberActionListener);
            m_unsubscribeButton.setEnabled(false);
        }
        return m_unsubscribeButton;
    }

    private JLabel getBidLabel(String currency, String source) {
        String name = "bid_label_" + currency + "_" + source;
        JLabel m_bidLabel = null;
        m_bidLabel = (JLabel)components.get(name);
        if (m_bidLabel==null) {
            m_bidLabel = new JLabel("Bid");
            m_bidLabel.setName(name);
            m_bidLabel.setPreferredSize(new Dimension(50, 50));
            m_bidLabel.addMouseListener(labelMouseListener);
            m_bidLabel.setEnabled(true);
            components.put(name, m_bidLabel);
        }
        return m_bidLabel;
    }

    private JLabel getOfferLabel(String currency, String source) {
        String name = "offer_label_" + currency + "_" + source;
        JLabel m_offerLabel = null;
        m_offerLabel = (JLabel)components.get(name);
        if (m_offerLabel==null) {
            m_offerLabel = new JLabel("Bid");
            m_offerLabel.setName(name);
            m_offerLabel.setPreferredSize(new Dimension(50, 50));
            m_offerLabel.addMouseListener(labelMouseListener);
            m_offerLabel.setEnabled(true);
            components.put(name, m_offerLabel);
        }
        return m_offerLabel;
    }

    private JTextArea getStatusField() {
        String name = "status";
        JTextArea m_status = null;
        m_status = (JTextArea)components.get(name);
        if (m_status==null) {
            m_status = new JTextArea();
            m_status.setName(name);
            Font font = new Font("Arial", Font.ITALIC, 14);
            m_status.setFont(font);
            m_status.setForeground(Color.BLUE);
            m_status.setEnabled(true);
            components.put(name, m_status);
        }
        return m_status;
    }


    Color[] colours = {Color.RED, Color.GREEN, Color.BLACK};
    final static int STATE_RED = 0;
    final static int STATE_GREEN = 1;
    final static int STATE_BLACK = 2;

    private static final int MIN_STATE_COLOR = STATE_RED;
    private static final int MAX_STATE_COLOR = STATE_BLACK;


    final ActionListener subscriberActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            final String instrumen1 = button.getName();
            final String[] instrument1 = instrumen1.split("_");
            final String instrument = instrument1[2];
            final String source = instrument1[3];
            final DataRequest.Builder dataRequestBuilder = client.createDataRequestForSubscription(1, instrument, PriceDataSourceType.valueOf(source));
            ((JTextArea) components.get("status")).setText("Subscribing PriceData for Instrument:" + instrument + " and Source:" + source);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Callback subScribeCallback = new Callback<PriceData, String>() {
                        @Override
                        public void success(PriceData success) {
                            //    System.out.println("Successfully subscribePriceData PriceData"
                            //            + success.toString());
                            String bidLabelName ="bid_label_" + success.getInstrument().getName() + "_" + success.getPriceDataSourceType();
                            String offerLabelName ="offer_label_" + success.getInstrument().getName() + "_" + success.getPriceDataSourceType();
                            ((JLabel) components.get(bidLabelName)).setText(success.getRateBid());

                            int bidRandom = new Random().nextInt(MAX_STATE_COLOR - MIN_STATE_COLOR + 1) + MIN_STATE_COLOR;
                            int offerRandom = new Random().nextInt(MAX_STATE_COLOR - MIN_STATE_COLOR + 1) + MIN_STATE_COLOR;
                            ((JLabel) components.get(bidLabelName)).setForeground(colours[bidRandom]);
                            ((JLabel) components.get(offerLabelName)).setText(success.getRateOffer());
                            ((JLabel) components.get(offerLabelName)).setForeground(colours[offerRandom]);
                            ((JLabel) components.get(bidLabelName)).updateUI();
                            ((JLabel) components.get(offerLabelName)).updateUI();
                        }

                        @Override
                        public void failure(String failure) {
                            ((JTextArea) components.get("status")).setText(failure);
                            //  System.out.println("Failure subscribePriceData"
                            //    + failure.toString());
                        }
                    };
                    ((JButton) (components.get("subscribe_button_" + instrument + "_" + source))).setEnabled(false);
                    ((JButton) (components.get("unsubscribe_button_" + instrument + "_" + source))).setEnabled(true);
                    String requestIdentifier = client.getRequestIdentifier();
                    currencySubscriptions.put(instrument, requestIdentifier);
                    try {
                        client.subcribeInstrument(currencySubscriptions.get(instrument), dataRequestBuilder, subScribeCallback);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });
        }
    };

    final ActionListener unSubscriberActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            final String instrumen1 = button.getName();
            final String[] instrument1 = instrumen1.split("_");
            final String instrument = instrument1[2];
            final String source = instrument1[3];
            final DataRequest.Builder dataRequestBuilder = client.createDataRequestForSubscription(1, instrument, PriceDataSourceType.valueOf(source));
            ((JTextArea) components.get("status")).setText("Requesting unsubscription for PriceData for Instrument:" + instrument + " and Source:" + source);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Callback unsubScribeCallback = new Callback<String, String>() {
                        @Override
                        public void success(String success) {

                            ((JLabel) components.get("bid_label_" + success + "_" + source)).setText("Bid");
                            ((JLabel) components.get("offer_label_" + success + "_" + source)).setText("Offer");
                            ((JLabel) components.get("bid_label_" + success + "_" + source)).setForeground(Color.BLACK);
                            ((JLabel) components.get("offer_label_" + success + "_" + source)).setForeground(Color.BLACK);
                            //  System.out.println("Successfully unsubscribePriceData PriceData"
                            //         + success.toString());
                        }

                        @Override
                        public void failure(String failure) {

                            ((JLabel) components.get("bid_label_" + failure + "_" + source)).setText("Bid");
                            ((JLabel) components.get("offer_label_" + failure + "_" + source)).setText("Offer");
                            ((JLabel) components.get("bid_label_" + failure + "_" + source)).setForeground(Color.BLACK);
                            ((JLabel) components.get("offer_label_" + failure + "_" + source)).setForeground(Color.BLACK);
                            ((JTextField) components.get("status")).setText("Failure un subscribePriceData"
                                    + failure.toString());
                            //    System.out.println("Failure unsubscribePriceData"
                            //           + failure.toString());
                        }
                    };
                    ((JButton) (components.get("subscribe_button_" + instrument + "_" + source))).setEnabled(true);
                    ((JButton) (components.get("unsubscribe_button_" + instrument + "_" + source))).setEnabled(false);
                    client.unsubcribeInstrument(currencySubscriptions.get(instrument), dataRequestBuilder, unsubScribeCallback);
                }
            });
        }


    };

    final MouseListener labelMouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                JLabel label = (JLabel) e.getSource();
                final String instrumen1 = label.getName();
                final String[] instrument1 = instrumen1.split("_");
                final String instrument = instrument1[2];
                final String source = instrument1[3];
                setHistoryRequested(instrument, source);

                final DataHistoryRequest.Builder dataRequestBuilder = client.createHistoryValues(1, instrument, PriceDataSourceType.valueOf(source), 50);
                ((JTextArea) components.get("status")).setText("Requesting PriceData History for  Instrument:" + instrument + " and Source:" + source);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Callback priceDataHistoryCallBack = new Callback<java.util.List<PriceData>, String>() {
                            @Override
                            public void success(java.util.List<PriceData> success) {
                                String name = "historyReq" + instrument + "_" + source;
                                if ((JButton) components.get(name) != null) {
                                    popUpPriceDataHistory((JLabel) components.get("bid_label_" + instrument + "_" + source), success, 50);
                                }
                          /*  ((JLabel) components.get("bid_label_" + success.getInstrument().getName() + "_" + success.getPriceDataSourceType())).setText(success.getRateBid());
                            ((JLabel) components.get("bid_label_" + success.getInstrument().getName() + "_" + success.getPriceDataSourceType())).setForeground(Color.BLUE);
                            ((JLabel) components.get("offer_label_" + success.getInstrument().getName() + "_" + success.getPriceDataSourceType())).setText(success.getRateOffer());
                            ((JLabel) components.get("offer_label_" + success.getInstrument().getName() + "_" + success.getPriceDataSourceType())).setForeground(Color.RED);*/

                            }

                            @Override
                            public void failure(String failure) {
                                ((JTextArea) components.get("status")).setText(failure);
                                //  System.out.println("Failure subscribePriceData"
                                //    + failure.toString());
                            }
                        };
                        ((JButton) (components.get("subscribe_button_" + instrument + "_" + source))).setEnabled(false);
                        ((JButton) (components.get("unsubscribe_button_" + instrument + "_" + source))).setEnabled(true);
                        String requestIdentifier = client.getRequestIdentifier();
                        client.getPriceDataHistory(requestIdentifier, dataRequestBuilder, priceDataHistoryCallBack);

                        //// popUpPriceDataHistory((JLabel) components.get("bid_label_" + instrument + "_" + source),new ArrayList<PriceData>());
                    }
                });
            }
        }
    };
    private JDialog m_dialog;
    private JPanel getIndividualSourceBoxes(JPanel container, String source, BBVAPriceTicker individualSource) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        container.add(panel);
        FlowLayout sourceFlowLayout = new FlowLayout(FlowLayout.CENTER, 20, 20);
        JPanel sourcePanel = new JPanel();
        sourcePanel.setLayout(sourceFlowLayout);
        sourcePanel.setBackground(Color.WHITE);
        JLabel sourceLabel = new JLabel(source);
        sourceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sourcePanel.add(sourceLabel);


        panel.add(sourcePanel);
        java.util.List<String> currencies = new ArrayList<>();
        currencies.add("AUDUSD");
        currencies.add("AUDJPY");
        currencies.add("AUDNZD");
        currencies.add("CADJPY");
        currencies.add("EURCHF");
        currencies.add("EURGBP");
        currencies.add("EURJPY");
        currencies.add("EURUSD");
        currencies.add("GBPJPY");
        currencies.add("GBPUSD");

        FlowLayout layout1 = new FlowLayout(FlowLayout.LEFT, 20, 20);
        for (String currency : currencies) {
            JPanel currencyPanel = new JPanel();
            currencyPanel.setBackground(Color.LIGHT_GRAY);
            currencyPanel.setLayout(layout1);
            currencyPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
            currencyPanel.add(new JLabel(currency));
            currencyPanel.add(individualSource.getSubscribeButton(currency, source));
            currencyPanel.add(individualSource.getBidLabel(currency, source));
            currencyPanel.add(individualSource.getOfferLabel(currency, source));
            currencyPanel.add(individualSource.getUnsubscribeButton(currency, source));
            layout1.addLayoutComponent(currency, currencyPanel);
            panel.add(currencyPanel);
        }
        return panel;
    }

    public void popUpPriceDataHistory(final Component component, final List<PriceData> history, int count) {
       /* String name = JOptionPane.showInputDialog(component,
        "What is your name?", null);*/
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JLabel label = (JLabel) component;
                final String instrumen1 = label.getName();
                final String[] instrument1 = instrumen1.split("_");
                final String instrument = instrument1[2];
                final String source = instrument1[3];
                final DataHistoryRequest.Builder dataRequestBuilder = client.createHistoryValues(1, instrument, PriceDataSourceType.valueOf(source), 50);

                ((JTextArea) components.get("status")).setText("Successfully Request PriceData History for  Instrument:" + instrument + " and Source:" + source);
                final PriceDataTableModel model = new PriceDataTableModel(history);
                if (m_dialog == null) {
                    m_dialog = new JDialog();
                    m_dialog.setAlwaysOnTop(true);
                    m_dialog.addWindowListener(new WindowAdapter() {
                        public void windowClosed(WindowEvent e) {
                            m_dialog = null;
                            System.out.println("jdialog window closed event received");
                            String name = "historyReq" + instrument + "_" + source;
                            components.remove(name);
                        }

                        public void windowClosing(WindowEvent e) {
                            m_dialog = null;
                            System.out.println("jdialog window closing event received");
                            String name = "historyReq" + instrument + "_" + source;
                            components.remove(name);
                        }
                    });

                    JPanel panel = new JPanel();

                    Dimension dimension =new Dimension(575, 600);
                    panel.setSize(new Dimension(dimension));
                    JTable allTable = new JTable(model);
                    TableRowSorter<TableModel> sorter
                            = new TableRowSorter<>(allTable.getModel());
                    allTable.setRowSorter(sorter);
                    allTable.setAutoCreateRowSorter(true);
                    allTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    allTable.getColumnModel().getColumn(0).setPreferredWidth(80);
                    allTable.getColumnModel().getColumn(1).setPreferredWidth(200);
                    allTable.getColumnModel().getColumn(2).setPreferredWidth(100);
                    allTable.getColumnModel().getColumn(3).setPreferredWidth(90);
                    allTable.getColumnModel().getColumn(4).setPreferredWidth(90);
                    JScrollPane scroll = new JScrollPane(allTable);
                    scroll.setBackground(Color.DARK_GRAY);
                    scroll.setSize(dimension);
                    panel.add(scroll);
                    m_dialog.pack();
                //   m_dialog.setLocationRelativeTo(getSubscribeButton("AUDUSD","SOURCE1"));
                    m_dialog.setSize(dimension);
                    m_dialog.setTitle("Price Data History for " + instrument + " and " + source);
                    m_dialog.add(scroll);
                    m_dialog.setVisible(true);
                }

            }
        });


        // TableData data = ((AllTableModel) allTable.getModel()).getTableData().get(selectedRow);


        // set data model for the table...

        // sets the popup menu for the table


    }

    public static void addComponentsToPane(Container container) {
        container.setSize(new Dimension(1400, 1400));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        BBVAPriceTicker individualSource = new BBVAPriceTicker();
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(Color.WHITE);
        // statusPanel.setLayout(layout1);
        statusPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        statusPanel.add(individualSource.getStatusField());

        container.add("statusBar", statusPanel);
        JPanel segmentsPanel = new JPanel();
        segmentsPanel.setLayout(new BoxLayout(segmentsPanel, BoxLayout.X_AXIS));
        container.add(segmentsPanel);



        //container.add(statusPanel);
        individualSource.getIndividualSourceBoxes(segmentsPanel, PriceDataSourceType.SOURCE1.toString(), individualSource);
        individualSource.getIndividualSourceBoxes(segmentsPanel, PriceDataSourceType.SOURCE2.toString(), individualSource);
        individualSource.getIndividualSourceBoxes(segmentsPanel, PriceDataSourceType.SOURCE3.toString(), individualSource);



    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("BBVA Price Ticker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    @Override
    public void setException(Throwable throwable) {
        m_throwable =throwable;
        getStatusField().setText(m_throwable.getLocalizedMessage());
    }
}
