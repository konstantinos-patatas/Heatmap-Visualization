# 🌡️ Java Swing Heatmap Visualizer

This project is a **Java Swing-based GUI application** that visualizes temperature data as a **heatmap**. It was developed as part of the **CE152 Object-Oriented Programming** module at the **University of Essex** during my first year of study. It marked my first experience building a desktop GUI application using Java Swing and served as a practical introduction to file handling, data visualization, and custom component rendering in Java.

---

## 📈 Project Overview

The application reads a data file containing **latitude**, **longitude**, and **temperature** values (in Fahrenheit), normalizes the coordinates to fit a drawing area within a `JFrame`, and visualizes the data as a heatmap using a **color gradient**.

### 🖼️ Core Functionality

- **Custom Paint Component:** A `JComponent` is used to render a pixel-based heatmap from the normalized coordinates and temperature values.
- **Color Mapping:** Temperatures are mapped to colors using either a **color gradient** (warm to cool) or **grayscale**, enhancing readability.
- **Zoom Controls:** Users can zoom in or out to analyze specific areas of the heatmap.
- **Display Mode Toggle:** Switch between **colored** and **grayscale** visualizations for accessibility and contrast.

---

## ✨ Key Features

- 🎨 **Java Swing GUI**: Built an interactive and user-friendly desktop interface.
- 🖌️ **Custom Paint Rendering**: Rendered temperature points as connected rectangles for smooth visualization.
- 📁 **File Handling**: Parsed structured temperature data from external `.csv` files.
- 🌈 **Color Gradients**: Visualized temperature data using dynamic gradient or grayscale mappings.
- 🔍 **Zooming Controls**: Allowed users to zoom in/out on specific data regions.

---

