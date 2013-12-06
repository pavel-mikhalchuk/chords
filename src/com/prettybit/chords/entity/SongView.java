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

import java.util.LinkedList;
import java.util.List;

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
            part.measure(60);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //test grid
        grid = new Grid(screenWidth, screenHeight, 5, 4);
        //test grid
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //test grid
        RectF partRect = new RectF(0, 0, 0, 0);
        grid.draw(canvas);
        //test grid

        canvas.scale(scale, scale);

        for (SongPart part : parts) {
            part.draw(canvas, new Caret());
            canvas.translate(0, part.height());

            //test grid
            partRect.right = part.width();
            partRect.bottom = part.height();
            grid.bind(partRect, part);

            partRect.top = part.height();
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
                grid.findCell(event.getX(), event.getY()).toggleActive();
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

        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
        }

        private Cell[][] cells;
        private int rows;
        private int columns;
        private float cellXSide;
        private float cellYSide;

        public Grid(float width, float height, int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            cellXSide = width / columns;
            cellYSide = height / rows;
            initCells();
        }

        public void draw(Canvas canvas) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    cells[r][c].draw(canvas, paint);
                }
            }
        }

        public void bind(RectF rect, Item item) {
            for (int i = rowsFormula(rect.top); i <= rowsFormula(rect.bottom); i++) {
                for (int j = columnsFormula(rect.left); j <= columnsFormula(rect.right); j++) {
                    cells[i][j].bind(item);
                }
            }
        }

        public Cell findCell(float x, float y) {
            return cells[rowsFormula(y)][columnsFormula(x)];
        }

        private int rowsFormula(float f) {return Math.min(rows - 1, (int) (f / cellYSide));}

        private int columnsFormula(float f) {return Math.min(columns - 1, (int) (f / cellXSide));}

        private void initCells() {
            int x = 0, y = 0;

            cells = new Cell[rows][columns];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    cells[r][c] = new Cell(new RectF(x, y, x + cellXSide, y + cellYSide), r, c);
                    x += cellXSide;
                }
                y += cellYSide;
                x = 0;
            }
        }

    }

    private static class Cell {

        private RectF r;
        private int row;
        private int column;
        private boolean active;
        private List<Item> bindedItems = new LinkedList<Item>();

        public Cell(RectF rect, int row, int column) {
            r = rect;
            this.row = row;
            this.column = column;
        }

        public RectF rect() {
            return r;
        }

        public int row() {
            return row;
        }

        public int column() {
            return column;
        }

        public void bind(Item item) {
            bindedItems.add(item);
        }

        public void draw(Canvas canvas, Paint paint) {
            if (active) {
                paint.setColor(Color.YELLOW);
                paint.setStyle(Paint.Style.FILL);
            }
            canvas.drawRect(r, paint);
            if (active) {
                paint.setColor(Color.BLACK);
                paint.setStyle(Paint.Style.STROKE);
            }
        }

        public void toggleActive() {
            active = !active;
        }

    }
    //test grid

}