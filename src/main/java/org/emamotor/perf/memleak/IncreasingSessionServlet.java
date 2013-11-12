package org.emamotor.perf.memleak;

import org.emamotor.perf.memleak.model.Memleak;
import org.emamotor.perf.memleak.model.MemleakContainer;
import org.emamotor.perf.memleak.util.RuntimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author emag
 */
@WebServlet(urlPatterns = {"/increasing-session"})
public class IncreasingSessionServlet extends HttpServlet {

    private static final long ADDING_OBJECT_NUM = Long.valueOf(System.getProperty(
            /* Property Name */ "org.emamotor.perf.memleak.adding_object_num",
            /* Default       */ "5000000"));

    private final int MB = 1024 * 1024;

    private static final Logger LOGGER = LoggerFactory.getLogger(IncreasingSessionServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        throw new UnsupportedOperationException("GET method is not supported");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        MemleakContainer memleakContainer = (MemleakContainer) session.getAttribute("memleakContainer");

        if (memleakContainer == null) {
            LOGGER.info("[Created new session]");
            memleakContainer = new MemleakContainer();
        }

        List<Memleak> memleaks = memleakContainer.getMemleaks();
        memleaks.addAll(createAddingMemleaks());
        memleakContainer.setMemleaks(memleaks);
        session.setAttribute("memleakContainer", memleakContainer);

        LOGGER.info("Adding object num: {}",    ADDING_OBJECT_NUM);
        LOGGER.info("Used  Heap Memory: {} MB", RuntimeUtil.getUsedHeapMemory() / MB);
        LOGGER.info("Total Heap Memory: {} MB", RuntimeUtil.getTotalMemory()    / MB);
        LOGGER.info("Free  Heap Memory: {} MB", RuntimeUtil.getFreeHeapMemory() / MB);
        LOGGER.info("Max   Heap Memory: {} MB", RuntimeUtil.getMaxMemory()      / MB);

    }

    private List<Memleak> createAddingMemleaks() {

        List<Memleak> addingMemleaks = new ArrayList<>();
        for (int i = 0; i < ADDING_OBJECT_NUM; i++) {
            addingMemleaks.add(new Memleak(i));
        }
        return addingMemleaks;

    }

}
