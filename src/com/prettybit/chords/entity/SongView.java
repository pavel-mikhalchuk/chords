package com.prettybit.chords.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Pavel Mikhalchuk
 */
public class SongView extends View {

    private float scale = 1;
    private List<SongPart> parts = new LinkedList<SongPart>();

    //test grid
    private Grid grid;
    //test grid

    public SongView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.CYAN);

        setOnTouchListener(new TouchListener(context));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        int screenHeight = MeasureSpec.getSize(heightMeasureSpec);

        for (SongPart part : parts) {
            part.init(screenWidth, screenHeight);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //test grid
        grid = new Grid(screenWidth, screenHeight, 5, 4);
        //test grid
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //test grid
        int i = 0;
        grid.draw(canvas);
        //test grid

        canvas.scale(scale, scale);

        for (SongPart part : parts) {
            part.draw(canvas);
            canvas.translate(0, part.size());

            //test grid
            grid.put(new RectF(0, part.size() * i++, part.screenWidth(), part.size()), part);
            //test grid
        }
    }

    public void addPart(SongPart part) {
        this.parts.add(part);
    }

    public void setScale(float scale) {
        this.scale *= scale;
    }

    private class TouchListener implements View.OnTouchListener {

        private ScaleGestureDetector d;

        private TouchListener(Context context) {
            d = new ScaleGestureDetector(context, new Scaler());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                grid.findCell(event.getX(), event.getY())
            }

            invalidate();

            return d.onTouchEvent(event);
        }

        private class Scaler extends ScaleGestureDetector.SimpleOnScaleGestureListener {

            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                setScale(detector.getScaleFactor());
                invalidate();
                return true;
            }

        }

    }

    //test grid
    private static class Grid {

        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); {paint.setStyle(Paint.Style.STROKE); paint.setColor(Color.BLACK);}

        private Cell[][] grid;
        private int rows;
        private int columns;
        private float cellXSize;
        private float cellYSize;

        private Map<Cell, List<SongPart>> elements = new HashMap<Cell, List<SongPart>>();

        public Grid(float width, float height, int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            cellXSize = width / columns;
            cellYSize = height / rows;
            initCells();
        }

        public void draw(Canvas canvas) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    grid[r][c].draw(canvas, paint);
                }
            }
        }

        public void put(RectF rect, SongPart part) {
            for (int i = rowsFormula(rect.top); i <= rowsFormula(rect.bottom); i++) {
                for (int j = columnsFormula(rect.left); i <= columnsFormula(rect.right); i++) {
                    put(grid[i][j], part);
                }
            }
        }

        public void put(Cell cell, SongPart part) {
            if (!elements.containsKey(cell)) {
                elements.put(cell, new LinkedList<SongPart>());
            }
            elements.get(cell).add(part);
        }

        public Cell findCell(float x, float y) {
            return grid[rowsFormula(y)][columnsFormula(x)];
        }

        private int rowsFormula(float f) {return formula(f, rows);}

        private int columnsFormula(float f) {return formula(f, columns);}

        private int formula(float f, int n) {
            return (int) f / n + (f % n > 0 ? 1 : 0);
        }

        private void initCells() {
            int x = 0, y = 0;

            grid = new Cell[rows][columns];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    grid[r][c] = new Cell(new RectF(x, y, x + cellXSize, y + cellYSize), r, c);
                    x += cellXSize;
                }
                y += cellYSize;
                x = 0;
            }
        }

    }

    private static class Cell {

        private RectF r;
        private int row;
        private int column;
        private boolean active;

        public Cell(RectF rect, int row, int column) {
            r = rect;
            this.row = row;
            this.column = column;
        }

        public void draw(Canvas canvas, Paint paint) {
            canvas.drawRect(r, paint);
        }

        private RectF rect() {
            return r;
        }

        private int row() {
            return row;
        }

        private int column() {
            return column;
        }

        public }
    //test grid

}