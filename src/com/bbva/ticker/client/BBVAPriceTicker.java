package com.bbva.ticker.client;

/**
 * Created by moham on 28/01/2016.
 */

import com.bbva.ticker.model.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class BBVAPriceTicker implements Client {
    private Map<String, Component> components = new HashMap<>();
    private Map<String, String> currencySubscriptions = new HashMap<>();
    private final PricingServiceClient client = new PricingServiceClient(this);
    private Throwable m_throwable;
    final java.util.Map<String, Integer> currencies = new LinkedHashMap<>();

    public BBVAPriceTicker() {

        currencies.put("AUDUSD", 1);
        currencies.put("AUDJPY", 2);
        currencies.put("AUDNZD", 3);
        currencies.put("CADJPY", 4);
        currencies.put("EURCHF", 5);
        currencies.put("EURGBP", 6);
        currencies.put("EURJPY", 7);
        currencies.put("EURUSD", 8);
        currencies.put("GBPJPY", 9);
        currencies.put("GBPUSD", 10);

    }


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
        m_subscribeButton = (JButton) components.get(name);
        if (m_subscribeButton == null) {
            m_subscribeButton = new JButton("Subscribe");
            m_subscribeButton.setName(name);
            m_subscribeButton.addActionListener(subscriberActionListener);
            components.put(name, m_subscribeButton);
            m_subscribeButton.setEnabled(true);

        }
        m_subscribeButton = (JButton) components.get(name);
        return m_subscribeButton;
    }

    private JButton getUnsubscribeButton(String currency, String source) {
        String name = "unsubscribe_button_" + currency + "_" + source;
        JButton m_unsubscribeButton = null;
        m_unsubscribeButton = (JButton) components.get(name);
        if (m_unsubscribeButton == null) {
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
        m_bidLabel = (JLabel) components.get(name);
        if (m_bidLabel == null) {
            m_bidLabel = new JLabel("Bid");
            m_bidLabel.setName(name);
            m_bidLabel.setPreferredSize(new Dimension(50, 50));
            m_bidLabel.addMouseListener(labelMouseListener);
            m_bidLabel.setEnabled(false);
            components.put(name, m_bidLabel);
        }
        return m_bidLabel;
    }

    private JLabel getOfferLabel(String currency, String source) {
        String name = "offer_label_" + currency + "_" + source;
        JLabel m_offerLabel = null;
        m_offerLabel = (JLabel) components.get(name);
        if (m_offerLabel == null) {
            m_offerLabel = new JLabel("Offer");
            m_offerLabel.setName(name);
            m_offerLabel.setPreferredSize(new Dimension(50, 50));
            m_offerLabel.addMouseListener(labelMouseListener);
            m_offerLabel.setEnabled(false);
            components.put(name, m_offerLabel);
        }
        return m_offerLabel;
    }

    private JTextArea getStatusField() {
        String name = "status";
        JTextArea m_status = null;
        m_status = (JTextArea) components.get(name);
        if (m_status == null) {
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


    Color[] colours = {Color.RED, Color.GREEN.darker(), Color.BLACK};
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
            int id =currencies.get(instrument);
            final DataRequest.Builder dataRequestBuilder = client.createDataRequestForSubscription(id, instrument, PriceDataSourceType.valueOf(source));
            ((JTextArea) components.get("status")).setText("Subscribing PriceData for Instrument:" + instrument + " and Source:" + source);
            final String bidLabelName = "bid_label_" + instrument + "_" + source;
            final String offerLabelName = "offer_label_" + instrument + "_" + source;

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Callback subScribeCallback = new Callback<PriceData, String>() {
                        @Override
                        public void success(PriceData success) {
                            //    System.out.println("Successfully subscribePriceData PriceData"
                            //            + success.toString());
                            ((JLabel) components.get(bidLabelName)).setText(success.getRateBid());
                            ((JLabel) components.get(bidLabelName)).setEnabled(true);
                            ((JLabel) components.get(offerLabelName)).setEnabled(true);
                            int bidRandom = new Random().nextInt(MAX_STATE_COLOR - MIN_STATE_COLOR + 1) + MIN_STATE_COLOR;
                            int offerRandom = new Random().nextInt(MAX_STATE_COLOR - MIN_STATE_COLOR + 1) + MIN_STATE_COLOR;
                            ((JLabel) components.get(bidLabelName)).setForeground(colours[bidRandom]);
                            ((JLabel) components.get(offerLabelName)).setText(success.getRateOffer());
                            ((JLabel) components.get(offerLabelName)).setForeground(colours[offerRandom]);
                            ((JLabel) components.get(bidLabelName)).updateUI();
                            ((JLabel) components.get(offerLabelName)).updateUI();

                            ((JTextArea) components.get("status")).setText("Receiving data for PriceData for Instrument:" + instrument + " and Source:" + source);
                        }

                        @Override
                        public void failure(String failure) {
                            ((JTextArea) components.get("status")).setText(failure);
                            ((JLabel) components.get(bidLabelName)).setEnabled(false);
                            ((JLabel) components.get(offerLabelName)).setEnabled(false);
                            ((JTextArea) components.get("status")).setText("Subscription Failed for PriceData for Instrument:" + instrument + " and Source:" + source + " " + failure);
                        }
                    };
                    ((JButton) (components.get("subscribe_button_" + instrument + "_" + source))).setEnabled(false);
                    ((JButton) (components.get("unsubscribe_button_" + instrument + "_" + source))).setEnabled(true);
                    String requestIdentifier = client.getRequestIdentifier();
                    currencySubscriptions.put(instrument, requestIdentifier);
                    try {
                        client.subcribeInstrument(currencySubscriptions.get(instrument), dataRequestBuilder, subScribeCallback);
                    } catch (Exception e) {
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
            final String bidLabelName = "bid_label_" + instrument + "_" + source;
            final String offerLabelName = "offer_label_" + instrument + "_" + source;
            int id =currencies.get(instrument);
            final DataRequest.Builder dataRequestBuilder = client.createDataRequestForSubscription(id, instrument, PriceDataSourceType.valueOf(source));
            ((JTextArea) components.get("status")).setText("Requesting unsubscription for PriceData for Instrument:" + instrument + " and Source:" + source);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Callback unsubScribeCallback = new Callback<String, String>() {
                        @Override
                        public void success(String success) {
                            ((JLabel) components.get(bidLabelName)).setText("Bid");
                            ((JLabel) components.get(offerLabelName)).setText("Offer");
                            ((JLabel) components.get(bidLabelName)).setForeground(Color.BLACK);
                            ((JLabel) components.get(offerLabelName)).setForeground(Color.BLACK);
                            ((JLabel) components.get(bidLabelName)).setEnabled(false);
                            ((JLabel) components.get(offerLabelName)).setEnabled(false);
                            ((JTextArea) components.get("status")).setText("Successfully Unsubscribed for PriceData for Instrument:" + instrument + " and Source:" + source);
                        }

                        @Override
                        public void failure(String failure) {
                            ((JLabel) components.get(bidLabelName)).setText("Bid");
                            ((JLabel) components.get(offerLabelName)).setText("Offer");
                            ((JLabel) components.get(bidLabelName)).setForeground(Color.BLACK);
                            ((JLabel) components.get(offerLabelName)).setForeground(Color.BLACK);
                            ((JTextField) components.get("status")).setText("Failure un subscribePriceData"
                                    + failure.toString());
                            ((JTextArea) components.get("status")).setText("Unsubscription Failed for PriceData for Instrument:" + instrument + " and Source:" + source + " " + failure);
                            ((JLabel) components.get(bidLabelName)).setEnabled(false);
                            ((JLabel) components.get(offerLabelName)).setEnabled(false);
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
            JLabel label = (JLabel) e.getSource();
            final String instrumen1 = label.getName();
            final String[] instrument1 = instrumen1.split("_");
            final String instrument = instrument1[2];
            final String source = instrument1[3];

            if (e.getClickCount() == 2 && label.isEnabled()) {
                setHistoryRequested(instrument, source);
                int id =currencies.get(instrument);
                final DataHistoryRequest.Builder dataRequestBuilder = client.createHistoryValues(id, instrument, PriceDataSourceType.valueOf(source), 50);
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
                            }

                            @Override
                            public void failure(String failure) {
                                ((JTextArea) components.get("status")).setText(failure);
                            }
                        };
                        String requestIdentifier = client.getRequestIdentifier();
                        client.getPriceDataHistory(requestIdentifier, dataRequestBuilder, priceDataHistoryCallBack);
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
        sourcePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sourcePanel.setLayout(sourceFlowLayout);
        sourcePanel.setBackground(Color.WHITE);
        JLabel sourceLabel = new JLabel(source);
        sourceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sourcePanel.add(sourceLabel);


        panel.add(sourcePanel);


        FlowLayout layout1 = new FlowLayout(FlowLayout.LEFT, 20, 20);
        for (Map.Entry<String, Integer> entry : currencies.entrySet()) {
            String currency = entry.getKey();
            JPanel currencyPanel = new JPanel();
            currencyPanel.setBorder(new TitledBorder(BorderFactory.createRaisedBevelBorder(), currency));
            currencyPanel.setBackground(Color.LIGHT_GRAY);
            currencyPanel.setLayout(layout1);
            currencyPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
            //currencyPanel.add(new JLabel(currency));
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
                final DataHistoryRequest.Builder dataRequestBuilder = client.createHistoryValues(currencies.get(instrument), instrument, PriceDataSourceType.valueOf(source), 50);

                ((JTextArea) components.get("status")).setText("Successfully Requested PriceData History for  Instrument:" + instrument + " and Source:" + source);
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

                    Dimension dimension = new Dimension(575, 600);
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
        m_throwable = throwable;
        getStatusField().setText(m_throwable.getLocalizedMessage());
    }
}
